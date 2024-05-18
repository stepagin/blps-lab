package com.annyarusova.subscriptionservice.service;

import com.annyarusova.subscriptionservice.dto.IssueInfo;
import com.annyarusova.subscriptionservice.dto.UserInfo;
import com.annyarusova.subscriptionservice.entity.IssueEntity;
import com.annyarusova.subscriptionservice.entity.UserEntity;
import com.annyarusova.subscriptionservice.mapper.IssueMapper;
import com.annyarusova.subscriptionservice.mapper.UserMapper;
import com.annyarusova.subscriptionservice.repository.IssueRepository;
import com.annyarusova.subscriptionservice.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final IssueRepository issueRepository;

    @KafkaListener(topics = "${spring.kafka.topics.user}",
            groupId = "spring.kafka.consumer.group-id", containerFactory = "userListenerContainerFactory")
    public void usersListener(@Payload UserInfo userInfo) {
        System.out.println("received message: " + userInfo);
        UserEntity userEntity = UserMapper.toEntity(userInfo);
        userRepository.save(userEntity);
    }

    @KafkaListener(topics = "${spring.kafka.topics.issue}",
            groupId = "spring.kafka.consumer.group-id", containerFactory = "issueListenerContainerFactory")
    public void issuesListener(@Payload IssueInfo issueInfo) {
        System.out.println("received message: " + issueInfo);
        // TODO: вынести в отдельный сервис и сделать @Transactional
        // TODO: сделать ControllerAdvice

        IssueEntity issueEntity = IssueMapper.toEntity(issueInfo);
        issueRepository.save(issueEntity);
    }

    // TODO: сделать обработку сообщений для удаления вопросов
}
