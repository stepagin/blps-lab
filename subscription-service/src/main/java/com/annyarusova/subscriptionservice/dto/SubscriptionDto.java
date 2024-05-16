package com.annyarusova.subscriptionservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private LocalDateTime notifyInterval;
}
