package ru.stepagin.blps.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.dto.PersonDto;
import ru.stepagin.blps.dto.RegistrationDto;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.mapper.PersonMapper;
import ru.stepagin.blps.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public PersonDto register(RegistrationDto data) {
        if (userRepository.existsByLoginIgnoreCase(data.getLogin()))
            throw new IllegalArgumentException("Логин " + data.getLogin() + " уже занят");
        UserEntity user = new UserEntity(data.getLogin(), data.getPassword(), data.getUsername());

        return PersonMapper.toDto(userRepository.save(user));
    }

    public List<PersonDto> getAllUsers() {
        return PersonMapper.toDto(userRepository.findAll());
    }
}

