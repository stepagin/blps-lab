package ru.stepagin.blps.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stepagin.blps.dto.AnswerDto;
import ru.stepagin.blps.dto.CreateAnswerDto;
import ru.stepagin.blps.dto.IssueDto;
import ru.stepagin.blps.entity.AnswerEntity;
import ru.stepagin.blps.entity.IssueEntity;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.exception.InvalidIdSuppliedException;
import ru.stepagin.blps.mapper.AnswerMapper;
import ru.stepagin.blps.mapper.IssueMapper;
import ru.stepagin.blps.repository.AnswerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final IssueService issueService;

    public AnswerDto getAnswerById(Long answerId) {
        AnswerEntity answerEntity = answerRepository.findById(answerId)
                .orElseThrow(() -> new InvalidIdSuppliedException("No answer with this id was found"));
        return AnswerMapper.toDto(answerEntity);
    }

    @Transactional
    public List<AnswerDto> getAnswersByIssueId(Long issueId) {
        return AnswerMapper.toDto(answerRepository.findByIssueId(issueId));
    }

    public IssueDto createAnswer(CreateAnswerDto answer, Long issueId, UserEntity author) {
        IssueEntity issue = issueService.getIssueEntityById(issueId);
        AnswerEntity answerEntity = new AnswerEntity(answer.getText(), author, issue);
        return IssueMapper.toDto(issue, List.of(answerRepository.save(answerEntity)));
    }

    public void deleteAnswer(Long answerId) {
        if (!answerRepository.existsById(answerId)) {
            throw new InvalidIdSuppliedException("No answer with this id was found");
        }
        answerRepository.deleteById(answerId);
    }

    public boolean isAnswerOwner(Long answerId, UserEntity user) {
        return answerRepository.existsByIdAndAuthor(answerId, user);
    }
}

