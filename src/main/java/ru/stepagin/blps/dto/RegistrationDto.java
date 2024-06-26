package ru.stepagin.blps.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RegistrationDto {
    @NotNull(message = "должно присутствовать")
    @Size(min = 4, max = 255, message = "длина должна быть от 4 до 255 символов")
    private final String username;
    @NotNull(message = "должно присутствовать")
    @Size(min = 4, max = 255, message = "длина должна быть от 4 до 255 символов")
    private final String login;
    @NotNull(message = "должно присутствовать")
    @Size(min = 6, max = 255, message = "длина должна быть от 6 до 255 символов")
    private final String password;
}
