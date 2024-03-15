package ru.stepagin.blps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.entity.IssueEntity;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.repository.IssueRepository;
import ru.stepagin.blps.repository.UserRepository;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    public IssueEntity createIssue(IssueEntity issue, Long authorId) {
        // Check if title is not empty
        if(issue.getTitle() == null || issue.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Issue title cannot be empty");
        }

        // Find author by id
        UserEntity author = userRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));

        // Additional logic for issue creation can be added here
        issue.setAuthor(author);

        return issueRepository.save(issue);
    }

    public IssueEntity getIssueById(Long issueId) {
        return issueRepository.findById(issueId).orElse(null);
    }

    public void deleteIssueById(Long issueId) {
        issueRepository.deleteById(issueId);
    }
}
