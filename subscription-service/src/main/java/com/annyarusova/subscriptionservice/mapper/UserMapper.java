package com.annyarusova.subscriptionservice.mapper;

import com.annyarusova.subscriptionservice.dto.UserInfo;
import com.annyarusova.subscriptionservice.entity.UserEntity;

public abstract class UserMapper {
    public static UserEntity toEntity(final UserInfo userInfo) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userInfo.getLogin());
        userEntity.setPassword(userInfo.getPassword());
        userEntity.setNickname(userInfo.getNickname());
        return userEntity;
    }
}
