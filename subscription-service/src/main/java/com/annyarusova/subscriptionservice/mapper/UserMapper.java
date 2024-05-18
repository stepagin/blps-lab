package com.annyarusova.subscriptionservice.mapper;

import com.annyarusova.subscriptionservice.dto.UserInfo;
import com.annyarusova.subscriptionservice.entity.UserEntity;

public abstract class UserMapper {
    public static UserEntity toEntity(final UserInfo userInfo) {
        return new UserEntity(userInfo.getLogin(), userInfo.getPassword(), userInfo.getNickname());
    }
}
