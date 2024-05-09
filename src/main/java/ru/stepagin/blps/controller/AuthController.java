package ru.stepagin.blps.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stepagin.blps.dto.PersonDto;
import ru.stepagin.blps.dto.RegistrationDto;
import ru.stepagin.blps.service.UserService;

import java.util.List;

@RestController
@RequestMapping("${api.endpoints.base-url}/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @Operation(description = "Создать пользователя")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated RegistrationDto user) {
        PersonDto registeredUser = userService.register(user);
        return ResponseEntity.ok(registeredUser);
    }

    @Operation(description = "Показать зарегистрированных пользователей")
    @GetMapping
    public ResponseEntity<List<PersonDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
