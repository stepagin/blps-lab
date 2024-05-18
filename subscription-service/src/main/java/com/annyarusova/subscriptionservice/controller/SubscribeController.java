package com.annyarusova.subscriptionservice.controller;

import com.annyarusova.subscriptionservice.dto.SubscriptionDto;
import com.annyarusova.subscriptionservice.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.endpoints.base-url}/subscription")
@CrossOrigin
@RequiredArgsConstructor
public class SubscribeController {
    private final SubscriptionService subscriptionService;

    @Operation(description = "Подписаться на рассылку")
    @PostMapping
    public ResponseEntity<SubscriptionDto> subscribe(@RequestBody @Validated SubscriptionDto subscription) {
        // TODO: по данным авторизации сохранить email в базу с пользователями. В БД с подпиской будут теги, id пользователя и остальное.
        return ResponseEntity.ok(subscriptionService.subscribe(subscription));
    }

    // TODO: unsubscribe
}

