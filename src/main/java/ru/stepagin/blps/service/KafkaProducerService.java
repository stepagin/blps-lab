package ru.stepagin.blps.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.dto.IssueDto;
import ru.stepagin.blps.entity.UserEntity;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<Long, IssueDto> issueKafkaTemplate;
    private final KafkaTemplate<String, UserEntity> userKafkaTemplate;
    @Value(value = "${app.kafka.topic-names.issue}")
    private String issueTopicName;
    @Value(value = "${app.kafka.topic-names.user}")
    private String userTopicName;

    public void sendIssue(IssueDto issueDto) {
        issueKafkaTemplate.send(issueTopicName, issueDto.getId(), issueDto);
        issueKafkaTemplate.flush();
    }

    public void sendUser(String login, UserEntity user) {
        userKafkaTemplate.send(userTopicName, login, user);
        userKafkaTemplate.flush();
    }
}
