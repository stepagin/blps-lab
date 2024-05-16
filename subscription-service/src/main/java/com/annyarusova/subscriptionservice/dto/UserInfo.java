package com.annyarusova.subscriptionservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserInfo {
    private String login;
    private String password;
    private String nickname;
}
