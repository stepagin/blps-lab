package ru.stepagin.blps.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Validated
public class IssueDto {
    private Long id;
    @NotNull(message = "должно присутствовать")
    @Size(min = 6, max = 255, message = "длина должна быть от 6 до 255 символов")
    private final String title;
    @NotNull(message = "должно присутствовать")
    @Size(max = 8000, message = "длина должна быть до 8000 символов")
    private final String description;
    private LocalDateTime date = LocalDateTime.now();
    private PersonDto author;
    private List<AnswerDto> answers;
}
