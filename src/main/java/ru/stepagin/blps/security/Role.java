package ru.stepagin.blps.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    MODERATOR("MODERATOR"),
    USER("USER");

    private final String vale;

    @Override
    public String getAuthority() {
        return vale;
    }

}
