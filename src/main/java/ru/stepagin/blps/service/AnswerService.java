package ru.stepagin.blps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.DTO.AnswerData;
import ru.stepagin.blps.DTO.IssueData;
import ru.stepagin.blps.entity.AnswerEntity;
import ru.stepagin.blps.entity.IssueEntity;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.exception.InvalidIdSuppliedException;
import ru.stepagin.blps.repository.AnswerRepository;
import ru.stepagin.blps.repository.UserRepository;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserRepository userRepository;

    public AnswerData getAnswerById(Long answerId) {
        AnswerEntity answerEntity = answerRepository.findById(answerId).orElseThrow(() -> new InvalidIdSuppliedException("No answer with this id was found"));
        return new AnswerData(answerEntity);
    }

    public IssueData getAnswersByIssueId(Long issueId) {
        IssueData issue = issueService.getIssueById(issueId);
        return new IssueData(issue, answerRepository.findByIssue_Id(issueId).stream().map(AnswerData::new).toList());
    }

    public AnswerData createAnswer(AnswerEntity answer, Long issueId, Long authorId) {
        if (answer.getText() == null || answer.getText().isEmpty()) {
            throw new IllegalArgumentException("Answer text cannot be empty");
        }

        IssueEntity issue = issueService.findIssueEntityById(issueId);

        UserEntity author = userRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));

        answer.setIssue(issue);
        answer.setAuthor(author);

        return new AnswerData(answerRepository.save(answer));
    }

    public void deleteAnswer(Long answerId) {
        if (!answerRepository.existsById(answerId)) {
            throw new InvalidIdSuppliedException("No answer with this id was found");
        }
        answerRepository.deleteById(answerId);
    }
}

