package com.annyarusova.subscriptionservice.controller;

import com.annyarusova.subscriptionservice.dto.SubscriptionDto;
import com.annyarusova.subscriptionservice.dto.UnsubscriptionDto;
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
    @PutMapping
    public ResponseEntity<SubscriptionDto> subscribe(@RequestBody @Validated SubscriptionDto subscription) {
        return ResponseEntity.ok(subscriptionService.subscribe(subscription));
    }

    @Operation(description = "Отписаться от рассылки")
    @DeleteMapping
    public ResponseEntity<UnsubscriptionDto> unsubscribe(@RequestBody UnsubscriptionDto unsubscription) {
        return ResponseEntity.ok(subscriptionService.unsubscribe(unsubscription));
    }

}

