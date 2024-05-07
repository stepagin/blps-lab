package ru.stepagin.blps.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Validated
public class PersonDto {
    @NotNull(message = "должно присутствовать")
    private String username;
}
