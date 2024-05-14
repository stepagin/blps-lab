package ru.stepagin.blps.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class IssueDto {
    private Long id;
    private final String title;
    private final String description;
    private LocalDateTime date = LocalDateTime.now();
    private PersonDto author;
    private List<AnswerDto> answers;
    private List<String> tags;
}
