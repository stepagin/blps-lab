package com.annyarusova.subscriptionservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class UserInfo {
    private String login;
    private String password;
    private String nickname;
}
