package ru.stepagin.blps.dto;

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
    private String text;
    private LocalDateTime createdAt;
}
