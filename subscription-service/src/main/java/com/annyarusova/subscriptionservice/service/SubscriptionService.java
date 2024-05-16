package com.annyarusova.subscriptionservice.service;

import com.annyarusova.subscriptionservice.dto.SubscriptionDto;
import com.annyarusova.subscriptionservice.entity.SubscriptionEntity;
import com.annyarusova.subscriptionservice.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionDto subscribe(SubscriptionDto subscriptionDto) {
        var subscription = new SubscriptionEntity(subscriptionDto.getEmail(), subscriptionDto.getTag(), subscriptionDto.getNotifyInterval(), LocalDateTime.now());
        subscriptionRepository.save(subscription);
        return subscriptionDto;
    }
}

