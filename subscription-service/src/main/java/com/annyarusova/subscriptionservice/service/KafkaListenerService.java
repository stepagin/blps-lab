package com.annyarusova.subscriptionservice.service;

import com.annyarusova.subscriptionservice.dto.IssueInfo;
import com.annyarusova.subscriptionservice.dto.UserInfo;
import com.annyarusova.subscriptionservice.entity.UserEntity;
import com.annyarusova.subscriptionservice.mapper.IssueMapper;
import com.annyarusova.subscriptionservice.mapper.UserMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class KafkaListenerService {
    private final UserService userService;
    private final IssueService issueService;

    @KafkaListener(topics = "${spring.kafka.topics.user}",
            groupId = "spring.kafka.consumer.group-id", containerFactory = "userListenerContainerFactory")
    public void usersListener(@Payload UserInfo userInfo) {
        System.out.println("received message: " + userInfo);
        UserEntity userEntity = UserMapper.toEntity(userInfo);
        userService.save(userEntity);
    }

    @KafkaListener(topics = "${spring.kafka.topics.issue}",
            groupId = "spring.kafka.consumer.group-id", containerFactory = "issueListenerContainerFactory")
    public void issuesListener(@Payload IssueInfo issueInfo) {
        System.out.println("received message: " + issueInfo);
        issueService.save(IssueMapper.toEntity(issueInfo));
    }

    // TODO: сделать обработку сообщений для удаления вопросов
}
