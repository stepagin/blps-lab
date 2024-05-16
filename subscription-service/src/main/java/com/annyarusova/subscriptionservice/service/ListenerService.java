package com.annyarusova.subscriptionservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ListenerService {

    @KafkaListener(topics = "${app.kafka.topic-names.user}",
            groupId = "spring.kafka.consumer.group-id", containerFactory = "userListenerContainerFactory")
    public void usersListener(@Payload String msg) { // TODO первым делом сделать @Payload UserInfo user
        // TODO реализовать сохранение в БД
        System.out.println("received message: " + msg);
    }

    // TODO Добавить листенер вопросов
}
