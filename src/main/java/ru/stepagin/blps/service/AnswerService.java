package ru.stepagin.blps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.entity.AnswerEntity;
import ru.stepagin.blps.entity.IssueEntity;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.repository.AnswerRepository;
import ru.stepagin.blps.repository.IssueRepository;
import ru.stepagin.blps.repository.UserRepository;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    public AnswerEntity getAnswerById(Long answerId) {
        return answerRepository.findById(answerId).orElse(null);
    }

    public List<AnswerEntity> getAnswersByIssueId(Long issueId) {
        return answerRepository.findByIssue_Id(issueId);
    }

    public AnswerEntity createAnswer(AnswerEntity answer, Long issueId, Long authorId) {
        // Check if text is not empty
        if (answer.getText() == null || answer.getText().isEmpty()) {
            throw new IllegalArgumentException("Answer text cannot be empty");
        }

        // Find issue by id
        IssueEntity issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new IllegalArgumentException("Issue not found"));

        // Check if author exists and is authenticated
        UserEntity author = userRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));

        // Additional logic for answer creation can be added here
        answer.setIssue(issue);
        answer.setAuthor(author);

        return answerRepository.save(answer);
    }

    public void deleteAnswer(Long answerId) {
        answerRepository.deleteById(answerId);
    }
}

