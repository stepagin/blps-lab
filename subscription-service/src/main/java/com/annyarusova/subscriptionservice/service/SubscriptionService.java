package com.annyarusova.subscriptionservice.service;

import com.annyarusova.subscriptionservice.dto.SubscriptionDto;
import com.annyarusova.subscriptionservice.entity.SubscriptionEntity;
import com.annyarusova.subscriptionservice.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    // TODO настроить JWT авторизацию на этот метод, реализовать получение данных юзера по JWT
    public SubscriptionDto subscribe(SubscriptionDto subDto) {
        SubscriptionEntity subscription = new SubscriptionEntity(subDto.getEmail(), subDto.getTag(), subDto.getNotifyInterval());
        // TODO почекать запись в БД, правильно ли зранятся данные
        subscriptionRepository.save(subscription);
        return subDto;
    }
}

