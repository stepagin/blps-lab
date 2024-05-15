package ru.stepagin.blps.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAnswerDto {
    @NotEmpty(message = "не должно быть пустым")
    @NotNull(message = "должно присутствовать")
    @Size(max = 8000, message = "длина должна быть до 8000 символов")
    private String text;
}
