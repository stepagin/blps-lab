package com.annyarusova.subscriptionservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionDto {
    @NotNull(message = "должно присутствовать")
    @NotEmpty(message = "не может быть пустым")
    @Size(min = 1, max = 255, message = "не может быть более 255 символов")
    private String email;
    @NotNull(message = "должно присутствовать")
    @NotEmpty(message = "не может быть пустым")
    private String tag;
    @NotNull(message = "должно присутствовать")
    @NotEmpty(message = "не может быть пустым")
    private int notifyInterval;

    public SubscriptionDto(String email, String tag, int days) {
        this.email = email;
        this.tag = tag;
        this.notifyInterval = days;
    }
}
