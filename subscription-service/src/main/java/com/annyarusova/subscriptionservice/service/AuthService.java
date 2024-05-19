package com.annyarusova.subscriptionservice.service;

import com.annyarusova.subscriptionservice.entity.UserEntity;
import com.annyarusova.subscriptionservice.security.JwtAuthentication;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

    @SneakyThrows
    public UserEntity getAuthenticatedUser() {
        return userService.getByLogin(getAuthInfo().getLogin()).orElseThrow(() -> new AuthException("Пользователь не выполнил вход"));
    }
}
