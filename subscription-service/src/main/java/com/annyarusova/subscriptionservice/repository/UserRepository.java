package com.annyarusova.subscriptionservice.repository;

import com.annyarusova.subscriptionservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLoginIgnoreCase(String username);

    @Transactional
    @Modifying
    @Query("update UserEntity u set u.email = :email where upper(u.login) = upper(:login)")
    void updateEmail(@Param("login") String login, @Param("email") String email);

    @Transactional
    @Modifying
    @Query("update UserEntity u set u.lastNotify = CURRENT_TIMESTAMP where upper(u.login) = upper(:login)")
    void updateLastNotify(@Param("login") String login);
}
