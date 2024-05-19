package com.annyarusova.subscriptionservice.service;

import com.annyarusova.subscriptionservice.entity.UserEntity;
import com.annyarusova.subscriptionservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public Optional<UserEntity> getByLogin(String login) {
        return userRepository.findByLoginIgnoreCase(login);
    }

    @Transactional
    public UserEntity saveIfNotExist(UserEntity user) {
        UserEntity savedUser = userRepository.findByLoginIgnoreCase(user.getLogin()).orElse(null);
        if (savedUser != null) {
            userRepository.updateEmail(savedUser.getLogin(), user.getEmail());
            savedUser.setEmail(user.getEmail());
            return savedUser;
        }
        return save(user);
    }
}
