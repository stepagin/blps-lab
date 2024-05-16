package ru.stepagin.blps.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stepagin.blps.config.SecurityConfiguration;
import ru.stepagin.blps.security.Role;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account")
@Getter
@Setter
@RequiredArgsConstructor
public class UserEntity {
    @Id
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String nickname;

    private Set<Role> roles = new HashSet<>();
    
    public UserEntity(String login, String passwordNotEncoded, String nickname) {
        this.login = login;
        this.password = SecurityConfiguration.passwordEncoder().encode(passwordNotEncoded);
        this.nickname = nickname;
        this.roles.add(Role.USER);
    }

}
