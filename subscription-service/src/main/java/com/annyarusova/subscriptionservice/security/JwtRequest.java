package com.annyarusova.subscriptionservice.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRequest {
    private String login;
    private String password;

}
