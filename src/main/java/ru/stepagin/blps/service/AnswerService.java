package ru.stepagin.blps.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.dto.AnswerDto;
import ru.stepagin.blps.dto.IssueDto;
import ru.stepagin.blps.entity.AnswerEntity;
import ru.stepagin.blps.entity.IssueEntity;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.exception.InvalidIdSuppliedException;
import ru.stepagin.blps.mapper.AnswerMapper;
import ru.stepagin.blps.mapper.IssueMapper;
import ru.stepagin.blps.repository.AnswerRepository;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final IssueService issueService;

    public AnswerDto getAnswerById(Long answerId) {
        AnswerEntity answerEntity = answerRepository.findById(answerId).orElseThrow(() -> new InvalidIdSuppliedException("No answer with this id was found"));
        return AnswerMapper.toDto(answerEntity);
    }

    public IssueDto getAnswersByIssueId(Long issueId) {
        IssueEntity issue = issueService.getIssueEntityById(issueId);
        return IssueMapper.toDto(issue, answerRepository.findByIssueId(issueId));
    }

    public AnswerDto createAnswer(AnswerDto answerDto, Long issueId, UserEntity author) {
        AnswerEntity answer = new AnswerEntity();
        answer.setText(answerDto.getText());
        answer.setAuthor(author);
        IssueEntity issue = issueService.getIssueEntityById(issueId);
        answer.setIssue(issue);
        return AnswerMapper.toDto(answerRepository.save(answer));
    }

    public void deleteAnswer(Long answerId) {
        if (!answerRepository.existsById(answerId)) {
            throw new InvalidIdSuppliedException("No answer with this id was found");
        }
        answerRepository.deleteById(answerId);
    }
}

