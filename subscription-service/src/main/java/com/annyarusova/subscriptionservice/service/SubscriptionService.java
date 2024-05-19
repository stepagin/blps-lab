package com.annyarusova.subscriptionservice.service;

import com.annyarusova.subscriptionservice.dto.SubscriptionDto;
import com.annyarusova.subscriptionservice.entity.SubscriptionEntity;
import com.annyarusova.subscriptionservice.entity.UserEntity;
import com.annyarusova.subscriptionservice.exception.SubscriptionAlreadyExistsException;
import com.annyarusova.subscriptionservice.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final AuthService authService;
    private final UserService userService;

    public SubscriptionDto subscribe(SubscriptionDto subDto) {
        UserEntity user = authService.getAuthenticatedUser();
        user.setEmail(subDto.getEmail());
        user = userService.saveIfNotExist(user);
        SubscriptionEntity subscription = new SubscriptionEntity(user, subDto.getTag(), subDto.getInterval());
        try {
            subscriptionRepository.save(subscription);
        } catch (DataIntegrityViolationException e) {
            throw new SubscriptionAlreadyExistsException("Вы уже подписались на тег " + subDto.getTag());
        }

        return subDto;
    }
}

