package com.annyarusova.subscriptionservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class NotificationDto {
    private List<String> titles;
}
