package ru.stepagin.blps.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.dto.IssueDto;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<Long, IssueDto> kafkaTemplate;

    public void sendIssue(IssueDto issueDto) {
        kafkaTemplate.send("issues", issueDto.getId(), issueDto);
        kafkaTemplate.flush();
    }
}
