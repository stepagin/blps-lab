package ru.stepagin.blps.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stepagin.blps.dto.CreateIssueDto;
import ru.stepagin.blps.dto.IssueDto;
import ru.stepagin.blps.entity.IssueEntity;
import ru.stepagin.blps.entity.IssueTagEntity;
import ru.stepagin.blps.entity.TagEntity;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.exception.IssueNotFoundException;
import ru.stepagin.blps.mapper.IssueMapper;
import ru.stepagin.blps.repository.AnswerRepository;
import ru.stepagin.blps.repository.IssueRepository;
import ru.stepagin.blps.repository.IssueTagRepository;
import ru.stepagin.blps.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class IssueService {
    private final IssueRepository issueRepository;
    private final AnswerRepository answerRepository;
    private final IssueTagRepository issueTagRepository;
    private final TagRepository tagRepository;

    @Transactional
    public IssueDto createIssue(CreateIssueDto issue, UserEntity user) {
        final IssueEntity issueEntity = issueRepository.save(new IssueEntity(issue.getTitle(), issue.getDescription(), user));
        for (String tagName : issue.getTags()) {
            tagName = tagName.trim().toLowerCase();
            TagEntity tagEntity = tagRepository.findTagByName(tagName);
            if (tagEntity == null) {
                if (!tagName.matches("[a-zA-Z0-9_-]+")) {
                    throw new IllegalArgumentException("Invalid tag name: " + tagName);
                }
                tagEntity = tagRepository.save(new TagEntity(tagName));
                issueTagRepository.save(new IssueTagEntity(issueEntity, tagEntity));
            } else if (!issueTagRepository.existsByIssueAndTagName(issueEntity, tagName)) {
                issueTagRepository.save(new IssueTagEntity(issueEntity, tagEntity));
            }
        }

        return IssueMapper.toDto(issueEntity, new ArrayList<>(), issue.getTags());
    }

    @Transactional
    public IssueDto getIssueById(Long issueId) {
        IssueEntity issue = getIssueEntityById(issueId);
        return IssueMapper.toDto(issue, answerRepository.findByIssueId(issueId), getTagsByIssueId(issueId));
    }

    public List<String> getTagsByIssueId(Long issueId) {
        return issueTagRepository.findByIssueId(issueId).stream().map(TagEntity::getName).toList();
    }

    public void deleteIssueById(Long issueId) {
        if (!issueRepository.existsById(issueId)) {
            throw new IssueNotFoundException("Issue not found: " + issueId);
        }
        issueTagRepository.deleteByIssueId(issueId);
        answerRepository.deleteByIssueId(issueId);
        issueRepository.deleteById(issueId);
    }

    protected IssueEntity getIssueEntityById(Long issueId) {
        return issueRepository.findById(issueId)
                .orElseThrow(() -> new IssueNotFoundException("Issue not found: " + issueId));
    }

    public boolean isIssueOwner(Long issueId, UserEntity user) {
        return issueRepository.existsByIdAndAuthor(issueId, user);
    }

    public boolean existsById(Long issueId) {
        return issueRepository.existsById(issueId);
    }
}
