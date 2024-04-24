package ru.stepagin.blps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.DTO.IssueData;
import ru.stepagin.blps.entity.IssueEntity;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.exception.InvalidIdSuppliedException;
import ru.stepagin.blps.exception.IssueNotFoundException;
import ru.stepagin.blps.repository.IssueRepository;
import ru.stepagin.blps.repository.UserRepository;

import java.util.List;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    public IssueData createIssue(IssueData issue, Long authorId) {
        // Check if title is not empty
        if (issue.getTitle().isEmpty())
            throw new IllegalArgumentException("Issue title cannot be empty");
        if (issue.getTitle().length() > 255)
            throw new IllegalArgumentException("Issue title cannot be longer than 255 symbols");
        if (issue.getDescription().isEmpty())
            throw new IllegalArgumentException("Description title cannot be empty");
        if (issue.getDescription().length() > 8000)
            throw new IllegalArgumentException("Description title cannot be longer than 8000 symbols");
        UserEntity author = userRepository.findById(authorId)
                .orElseThrow(() -> new InvalidIdSuppliedException("Author not found"));
        IssueEntity issueEntity = new IssueEntity(issue.getTitle(), issue.getDescription(), author);

        return new IssueData(issueRepository.save(issueEntity));
    }

    public IssueData getIssueById(Long issueId) {
        IssueEntity issue;
        issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new IssueNotFoundException("No issue with this id was found"));
        return new IssueData(issue);
    }

    public void deleteIssueById(Long issueId) {
        if (!issueRepository.existsById(issueId))
            throw new IssueNotFoundException("No issue with this id was found");
        issueRepository.deleteById(issueId);
    }

    public List<IssueData> getAll() {
        List<IssueEntity> issueEntities = issueRepository.findAll();
        return issueEntities.stream().map(IssueData::new).toList();
    }

    public IssueEntity findIssueEntityById(Long issueId) {
        return issueRepository.findById(issueId)
                .orElseThrow(() -> new IssueNotFoundException("Issue not found"));
    }
}
