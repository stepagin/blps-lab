package ru.stepagin.blps.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.dto.IssueDto;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<Long, IssueDto> issueKafkaTemplate;
    private final KafkaTemplate<String, String> userKafkaTemplate;
    @Value(value = "${app.kafka.topic-names.issue}")
    private String issueTopicName;
    @Value(value = "${app.kafka.topic-names.user}")
    private String userTopicName;

    public void sendIssue(IssueDto issueDto) {
        issueKafkaTemplate.send(issueTopicName, issueDto.getId(), issueDto);
        issueKafkaTemplate.flush();
    }

    public void sendUserEmail(String login, String userEmail) {
        userKafkaTemplate.send(userTopicName, login, userEmail);
        userKafkaTemplate.flush();
    }
}
