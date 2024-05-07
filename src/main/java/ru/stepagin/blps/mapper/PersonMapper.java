package ru.stepagin.blps.mapper;

import ru.stepagin.blps.dto.PersonDto;
import ru.stepagin.blps.entity.UserEntity;

import java.util.List;

public abstract class PersonMapper {
    public static PersonDto toDto(UserEntity user) {
        PersonDto personDto = new PersonDto();
        personDto.setUsername(user.getNickname());
        return personDto;
    }

    public static List<PersonDto> toDto(List<UserEntity> users) {
        return users.stream().map((PersonMapper::toDto)).toList();
    }
}
