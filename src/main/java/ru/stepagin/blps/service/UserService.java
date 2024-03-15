package ru.stepagin.blps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public UserEntity login(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    public UserEntity register(UserEntity user) {
        // Check if login is unique
        if (userRepository.existsByLogin(user.getLogin())) {
            throw new IllegalArgumentException("Login is already taken");
        }

        // Check if nickname is unique
        if (userRepository.existsByNickname(user.getNickname())) {
            throw new IllegalArgumentException("Nickname is already taken");
        }

        // Additional logic for registration can be added here
        return userRepository.save(user);
    }
}

