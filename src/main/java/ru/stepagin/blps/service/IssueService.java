package ru.stepagin.blps.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stepagin.blps.dto.IssueDto;
import ru.stepagin.blps.entity.IssueEntity;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.exception.IssueNotFoundException;
import ru.stepagin.blps.mapper.IssueMapper;
import ru.stepagin.blps.repository.IssueRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class IssueService {
    private final IssueRepository issueRepository;

    public IssueDto createIssue(IssueDto issue, UserEntity user) {
        IssueEntity issueEntity = new IssueEntity(issue.getTitle(), issue.getDescription(), user);
        return IssueMapper.toDto(issueRepository.save(issueEntity));
    }

    public IssueDto getIssueById(Long issueId) {
        IssueEntity issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new IssueNotFoundException("No issue with this id was found"));
        // TODO: достать ответы на вопрос и передать в toDto
        return IssueMapper.toDto(issue);
    }

    @Transactional
    public void deleteIssueById(Long issueId) {
        if (!issueRepository.existsById(issueId))
            throw new IssueNotFoundException("No issue with this id was found");
        issueRepository.deleteById(issueId);
    }

    public List<IssueDto> getAll() {
        return IssueMapper.toDto(issueRepository.findAll());
    }

    public IssueEntity getIssueEntityById(Long issueId) {
        return issueRepository.findById(issueId)
                .orElseThrow(() -> new IssueNotFoundException("Issue not found"));
    }

    public boolean isIssueOwner(Long issueId, UserEntity user) {
        return issueRepository.findByIdAndAuthor(issueId, user) != null;
    }
}
