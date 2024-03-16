package ru.stepagin.blps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.DTO.UserLoginContext;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public UserLoginContext login(String login, String password) {
        UserEntity u = userRepository.findByLoginAndPassword(login, password);
        if (u == null) {
            throw new IllegalArgumentException("invalid username or password");
        } else {
            return new UserLoginContext(u);
        }
    }

    public UserLoginContext register(UserEntity user) {
        if (user.getLogin() == null || user.getNickname() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Login, nickname, and password are required for registration");
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
        return new UserLoginContext(userRepository.save(user));
    }
}

