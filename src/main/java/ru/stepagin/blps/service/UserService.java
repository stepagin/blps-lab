package ru.stepagin.blps.service;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.dto.PersonDto;
import ru.stepagin.blps.dto.RegistrationDto;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.mapper.PersonMapper;
import ru.stepagin.blps.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final KafkaProducerService kafkaProducerService;

    public PersonDto register(RegistrationDto data) {
        if (userRepository.existsByLoginIgnoreCase(data.getLogin()))
            throw new IllegalArgumentException("Логин " + data.getLogin() + " уже занят");
        UserEntity user = new UserEntity(data.getLogin(), data.getPassword(), data.getUsername());
        PersonDto personDto = PersonMapper.toDto(userRepository.save(user));
        kafkaProducerService.sendUser(data.getLogin(), user);
        return personDto;
    }

    public List<PersonDto> getAllUsers() {
        return PersonMapper.toDto(userRepository.findAll());
    }

    public Optional<UserEntity> getByLogin(@NonNull String login) {
        return userRepository.findByLoginIgnoreCase(login);
    }
}

