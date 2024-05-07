package ru.stepagin.blps.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@Validated
public class AnswerDto {
    private Long id;
    private String author;
    @NotNull(message = "должно присутствовать")
    @Size(max = 8000, message = "длина должна быть до 8000 символов")
    private String text;
    private LocalDateTime createdAt;
}
