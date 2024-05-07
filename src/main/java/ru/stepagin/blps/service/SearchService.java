package ru.stepagin.blps.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stepagin.blps.repository.IssueRepository;
import ru.stepagin.blps.repository.IssueTagRepository;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final IssueRepository issueRepository;
    private final IssueTagRepository issueTagRepository;
//
//    public List<IssueDto> searchIssuesByTitle(String title) {
//        List<IssueEntity> issueEntities = issueRepository.findByTitleContainingIgnoreCase(title);
//        return IssueMapper.toDto(issueEntities);
//    }
//
//    public List<IssueDto> searchIssuesByTags(List<String> tags) {
//        // такой метод надо либо удалить, либо допилить
//        List<IssueDto> issues = new ArrayList<>();
//
//        for (String tagName : tags) {
//            List<IssueTagEntity> issueTags = issueTagRepository.findByTagName(tagName); //findIssueTagEntitiesByTag_Name(tagName);
//
//            for (IssueTagEntity issueTag : issueTags) {
//                issues.add(IssueMapper.toDto(issueTag.getIssue()));
//            }
//        }
//
//        return issues;
//    }
//
//    public List<IssueDto> searchByTitleAndTags(String title, List<String> tags) {
//        return issueTagRepository.findByTitleAndTags(title, tags).stream()
//                .map((issueTagEntity -> IssueMapper.toDto(issueTagEntity.getIssue()))).toList();
//    }
//
//    public List<IssueDto> search(String title, List<String> tags) {
//        if (title != null && !title.isEmpty() && tags != null && !tags.isEmpty()) {
//            return searchByTitleAndTags(title, tags);
//        } else if (title != null && !title.isEmpty()) {
//            return searchIssuesByTitle(title);
//        } else if (tags != null && !tags.isEmpty()) {
//            return searchIssuesByTags(tags);
//        } else {
//            throw new IllegalArgumentException("Не указаны ни строка поиска, ни теги");
//        }
//
//    }
}

