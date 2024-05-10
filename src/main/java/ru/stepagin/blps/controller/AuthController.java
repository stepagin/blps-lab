package ru.stepagin.blps.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stepagin.blps.dto.*;
import ru.stepagin.blps.service.AuthService;
import ru.stepagin.blps.service.UserService;

import java.util.List;

@RestController
@RequestMapping("${api.endpoints.base-url}/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @Operation(description = "Создать пользователя")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated RegistrationDto user) {
        PersonDto registeredUser = userService.register(user);
        return ResponseEntity.ok(registeredUser);
    }

    @Operation(description = "Показать зарегистрированных пользователей")
    @GetMapping
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ResponseEntity<List<PersonDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
}
