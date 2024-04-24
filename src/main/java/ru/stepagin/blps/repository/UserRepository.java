package ru.stepagin.blps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepagin.blps.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByNickname(String nickname);

    boolean existsByLogin(String login);

    UserEntity findByLoginAndPassword(String login, String password);

}