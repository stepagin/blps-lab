package com.annyarusova.subscriptionservice.controller;

import com.annyarusova.subscriptionservice.dto.SubscriptionDto;
import com.annyarusova.subscriptionservice.dto.UnsubscriptionDto;
import com.annyarusova.subscriptionservice.service.NotificationService;
import com.annyarusova.subscriptionservice.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.endpoints.api-version}/subscription")
@CrossOrigin
@RequiredArgsConstructor
public class SubscribeController {
    private final SubscriptionService subscriptionService;
    private final NotificationService notificationService;

    @Operation(description = "Подписаться на рассылку")
    @PutMapping
    @SneakyThrows
    public ResponseEntity<SubscriptionDto> subscribe(@RequestBody @Validated SubscriptionDto subscription) {
        return ResponseEntity.ok(subscriptionService.subscribe(subscription));
    }

    @Operation(description = "Отписаться от рассылки")
    @DeleteMapping
    @SneakyThrows
    public ResponseEntity<UnsubscriptionDto> unsubscribe(@RequestBody UnsubscriptionDto unsubscription) {
        return ResponseEntity.ok(subscriptionService.unsubscribe(unsubscription));
    }

    @Operation(description = "Запустить рассылку дайджестов вручную")
    @GetMapping
    public ResponseEntity<String> sendNotifications() {
        notificationService.sendNotifications();
        return ResponseEntity.ok("Рассылка успешно проведена.");
    }
}

