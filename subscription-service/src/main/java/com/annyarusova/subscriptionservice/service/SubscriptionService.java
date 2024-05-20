package com.annyarusova.subscriptionservice.service;

import com.annyarusova.subscriptionservice.dto.SubscriptionDto;
import com.annyarusova.subscriptionservice.dto.UnsubscriptionDto;
import com.annyarusova.subscriptionservice.entity.SubscriptionEntity;
import com.annyarusova.subscriptionservice.entity.UserEntity;
import com.annyarusova.subscriptionservice.exception.SubscriptionAlreadyExistsException;
import com.annyarusova.subscriptionservice.repository.NotificationInterfaceDto;
import com.annyarusova.subscriptionservice.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final AuthService authService;
    private final UserService userService;

    @Transactional
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

    @Transactional
    public UnsubscriptionDto unsubscribe(UnsubscriptionDto unsubDto) {
        UserEntity user = authService.getAuthenticatedUser();
        if (unsubDto.getTag() == null || unsubDto.getTag().isEmpty()) {
            subscriptionRepository.unsubscribeAll(user.getLogin());
            return unsubDto;
        }
        unsubDto.setTag(unsubDto.getTag().trim());
        if (!unsubDto.getTag().matches("[a-zA-Z0-9_-]+")) {
            throw new IllegalArgumentException("Указан невалидный тег: \"" + unsubDto.getTag() + "\"");
        }
        if (subscriptionRepository.unsubscribeByTag(user.getLogin(), unsubDto.getTag()) == 0) {
            throw new IllegalArgumentException("Вы не подписаны на тег " + unsubDto.getTag());
        }
        return unsubDto;
    }

    public List<NotificationInterfaceDto> getReadyNotifications() {
        return subscriptionRepository.findNotifications();
    }
}

