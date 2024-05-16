package com.annyarusova.subscriptionservice.service;

import com.annyarusova.subscriptionservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final SubscriptionService subscriptionService;
    private final UserRepository userRepository;

    public void sendNotifications() {
        // TODO реализовать нотификацию (дайджест)
        // TODO настроить нотификацию каждые 3-5 минут в QUARTZ, чтобы можно было проверить на паре
    }
}
