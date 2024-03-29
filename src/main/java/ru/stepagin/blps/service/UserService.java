package ru.stepagin.blps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.DTO.UserData;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public UserData login(String login, String password) {
        UserEntity u = userRepository.findByLoginAndPassword(login, password);
        if (u == null) {
            throw new IllegalArgumentException("invalid username or password");
        } else {
            return new UserData(u);
        }
    }

    public UserData register(UserEntity user) {
        if (user.getLogin() == null || user.getNickname() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Login, nickname, and password are required for registration");
        }
        if (user.getLogin().length() > 255) {
            throw new IllegalArgumentException("Login cannot be longer than 255 symbols");
        }
        if (user.getNickname().length() > 255) {
            throw new IllegalArgumentException("Nickname cannot be longer than 255 symbols");
        }
        if (user.getPassword().length() > 255) {
            throw new IllegalArgumentException("Password cannot be longer than 255 symbols");
        }

        // Check if login is unique
        if (userRepository.existsByLogin(user.getLogin())) {
            throw new IllegalArgumentException("Login is already taken");
        }

        // Check if nickname is unique
        if (userRepository.existsByNickname(user.getNickname())) {
            throw new IllegalArgumentException("Nickname is already taken");
        }

        // Additional logic for registration can be added here
        return new UserData(userRepository.save(user));
    }
}

