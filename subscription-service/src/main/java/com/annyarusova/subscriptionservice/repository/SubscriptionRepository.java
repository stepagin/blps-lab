package com.annyarusova.subscriptionservice.repository;

import com.annyarusova.subscriptionservice.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
    @Transactional
    @Modifying
    @Query("delete from SubscriptionEntity s where s.user.login = :login")
    void unsubscribeAll(@Param("login") String login);

    @Transactional
    @Modifying
    @Query("delete from SubscriptionEntity s where s.user.login = :login and upper(s.tag) = upper(:tag)")
    int unsubscribeByTag(@Param("login") String login, String tag);
}
