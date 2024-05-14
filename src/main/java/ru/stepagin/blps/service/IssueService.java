package ru.stepagin.blps.service;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stepagin.blps.dto.CreateIssueDto;
import ru.stepagin.blps.dto.IssueDto;
import ru.stepagin.blps.entity.IssueEntity;
import ru.stepagin.blps.entity.TagEntity;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.exception.IssueNotFoundException;
import ru.stepagin.blps.mapper.IssueMapper;
import ru.stepagin.blps.repository.AnswerRepository;
import ru.stepagin.blps.repository.IssueRepository;
import ru.stepagin.blps.repository.IssueTagRepository;
import ru.stepagin.blps.repository.TagRepository;

import javax.transaction.*;
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
        tagRepository.saveAll(issue.getTags().stream().map(TagEntity::new).toList());
        IssueEntity issueEntity = new IssueEntity(issue.getTitle(), issue.getDescription(), user);
        return IssueMapper.toDto(issueRepository.save(issueEntity), new ArrayList<>(), issue.getTags());
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
        BitronixTransactionManager btm = TransactionManagerServices.getTransactionManager();
        try {
            btm.begin();
            issueTagRepository.deleteByIssue(issueId);
            answerRepository.deleteByIssueId(issueId);
            if (!issueRepository.existsById(issueId)) {
                btm.rollback();
                throw new IssueNotFoundException(issueId.toString());
            }
            issueRepository.deleteById(issueId);
            btm.commit();
        } catch (NotSupportedException |
                 SystemException |
                 HeuristicRollbackException |
                 HeuristicMixedException |
                 RollbackException e) {
            throw new RuntimeException(e);
        }
    }

    protected IssueEntity getIssueEntityById(Long issueId) {
        return issueRepository.findById(issueId)
                .orElseThrow(() -> new IssueNotFoundException(issueId.toString()));
    }

    public boolean isIssueOwner(Long issueId, UserEntity user) {
        return issueRepository.existsByIdAndAuthor(issueId, user);
    }
}
