package com.annyarusova.subscriptionservice.repository;

import com.annyarusova.subscriptionservice.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
    @Transactional
    @Modifying
    @Query("delete from SubscriptionEntity s where s.user.login = :login")
    void unsubscribeAll(@Param("login") String login);

    @Transactional
    @Modifying
    @Query("delete from SubscriptionEntity s where s.user.login = :login and upper(s.tag) = upper(:tag)")
    int unsubscribeByTag(@Param("login") String login, String tag);

    // потенциально этот запрос можно переписать на Hibernate, но так тоже неплохо
    @Query(nativeQuery = true, value = """
            select\s
              r1.person as person,\s
              r1.nickname as nickname,\s
              r1.email as email,\s
              array_agg(r1.issue_id) as issues,\s
              array_agg(r1.title) as titles\s
            from\s
              (
                select\s
                  distinct s.person,\s
                  p.nickname,\s
                  p.email,\s
                  iet.issue_entity_id as issue_id,\s
                  i.title\s
                from\s
                  subscription.subscription as s\s
                  join subscription.issue_entity_tags as iet on s.tag = iet.tags\s
                  join subscription.issue as i on i.id = iet.issue_entity_id\s
                  join subscription.person as p on p.login = s.person\s
                where\s
                  p.last_notify + (INTERVAL '1' SECOND * s.interval) < i.creation_date
              ) as r1\s
            group by\s
              r1.person,\s
              r1.nickname,\s
              r1.email
            """)
    List<NotificationInterfaceDto> findNotifications();
}
