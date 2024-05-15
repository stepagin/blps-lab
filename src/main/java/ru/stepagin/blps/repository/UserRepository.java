package ru.stepagin.blps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.stepagin.blps.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByLoginIgnoreCase(@NonNull String login);

    Optional<UserEntity> findByLoginIgnoreCase(@NonNull String login);
}