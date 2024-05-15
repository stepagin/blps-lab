package ru.stepagin.blps.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class AnswerDto {
    private Long id;
    private String author;
    private String text;
    private LocalDateTime createdAt;
}
