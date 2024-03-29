package ru.stepagin.blps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.DTO.IssueData;
import ru.stepagin.blps.entity.IssueEntity;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.repository.IssueRepository;
import ru.stepagin.blps.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private UserRepository userRepository;

    public IssueData createIssue(IssueData issue, Long authorId) {
        // Check if title is not empty
        if (issue.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Issue title cannot be empty");
        }
        if (issue.getTitle().length() > 255) {
            throw new IllegalArgumentException("Issue title cannot be longer than 255 symbols");
        }
        if (issue.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description title cannot be empty");
        }
        if (issue.getDescription().length() > 8000) {
            throw new IllegalArgumentException("Description title cannot be longer than 8000 symbols");
        }

        // Find author
        UserEntity author = userRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setTitle(issue.getTitle());
        issueEntity.setDescription(issue.getDescription());
        issueEntity.setAuthor(author);
        issueEntity.setDate(LocalDateTime.now());

        return new IssueData(issueRepository.save(issueEntity));
    }

    public IssueData getIssueById(Long issueId) {
        try {
            IssueEntity issue = issueRepository.findById(issueId).orElse(null);
            if (issue == null) {
                return null;
            }
            return new IssueData(issue);
        } catch (Exception e) {
            throw new IllegalArgumentException("Internal error while finding issue");
        }
    }

    public void deleteIssueById(Long issueId) {
        try {
            issueRepository.deleteById(issueId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Internal error while deleting issue");
        }

    }

    public List<IssueData> getAll() {
        try {
            List<IssueEntity> issueEntities = issueRepository.findAll();
            List<IssueData> issueDataList = new ArrayList<>();
            for (IssueEntity ie : issueEntities) {
                issueDataList.add(new IssueData(ie));
            }
            return issueDataList;
        } catch (Exception e) {
            throw new IllegalArgumentException("Internal error while getting issues");
        }
    }
}
