package ru.stepagin.blps.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateIssueDto {
    @NotNull(message = "должно присутствовать")
    @NotEmpty(message = "не может быть пустым")
    @Size(min = 1, max = 255, message = "не может быть более 255 символов")
    private String title;
    @NotNull(message = "должно присутствовать")
    @NotEmpty(message = "не может быть пустым")
    @Size(max = 8000, message = "не может быть более 8000 символов")
    private String description;
    private List<String> tags = new ArrayList<>();
}
