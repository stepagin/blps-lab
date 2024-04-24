package ru.stepagin.blps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepagin.blps.DTO.UserData;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String login, @RequestParam String password) {
        UserData user = userService.login(login, password);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user) {
        UserData registeredUser = userService.register(user);
        return ResponseEntity.ok(registeredUser);
    }
}
