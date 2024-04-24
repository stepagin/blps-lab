package ru.stepagin.blps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.DTO.IssueData;
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

    public List<IssueData> searchIssuesByTitle(String title) {
        List<IssueEntity> issueEntities = issueRepository.findByTitleContainingIgnoreCase(title);
        return issueEntities.stream().map(IssueData::new).toList();
    }

    public List<IssueData> searchIssuesByTags(List<String> tags) {
        // такой метод надо либо удалить, либо допилить
        List<IssueData> issues = new ArrayList<>();

        for (String tagName : tags) {
            List<IssueTagEntity> issueTags = issueTagRepository.findByTag_Name(tagName); //findIssueTagEntitiesByTag_Name(tagName);

            for (IssueTagEntity issueTag : issueTags) {
                issues.add(new IssueData(issueTag.getIssue()));
            }
        }

        return issues;
    }
}

