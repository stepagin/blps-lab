package ru.stepagin.blps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.entity.IssueEntity;
import ru.stepagin.blps.entity.IssueTagEntity;
import ru.stepagin.blps.repository.IssueRepository;
import ru.stepagin.blps.repository.IssueTagRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private IssueTagRepository issueTagRepository;

    public List<IssueEntity> searchIssuesByTitle(String title) {
        return issueRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<IssueEntity> searchIssuesByTags(List<String> tags) {
        List<IssueEntity> issues = new ArrayList<>();

        for (String tagName : tags) {
            List<IssueTagEntity> issueTags = issueTagRepository.findByTag_Name(tagName); //findIssueTagEntitiesByTag_Name(tagName);

            for (IssueTagEntity issueTag : issueTags) {
                issues.add(issueTag.getIssue());
            }
        }

        return issues;
    }
}

