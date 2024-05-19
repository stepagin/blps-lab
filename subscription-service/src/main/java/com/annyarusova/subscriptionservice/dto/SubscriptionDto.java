package com.annyarusova.subscriptionservice.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionDto {
    @NotNull(message = "должно присутствовать")
    @NotEmpty(message = "не может быть пустым")
    @Size(min = 1, max = 255, message = "не может быть более 255 символов")
    @Email
    private String email;
    @NotNull(message = "должно присутствовать")
    @NotEmpty(message = "не может быть пустым")
    private String tag;
    @NotNull(message = "должно присутствовать")
    @Min(value = 1)
    @Max(value = 30)
    private int interval;

    public SubscriptionDto(String email, String tag, int minutes) {
        this.email = email;
        this.tag = tag;
        this.interval = minutes;
    }
}
