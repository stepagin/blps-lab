package ru.stepagin.blps.service;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stepagin.blps.dto.CreateIssueDto;
import ru.stepagin.blps.dto.IssueDto;
import ru.stepagin.blps.entity.IssueEntity;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.exception.IssueNotFoundException;
import ru.stepagin.blps.mapper.IssueMapper;
import ru.stepagin.blps.repository.AnswerRepository;
import ru.stepagin.blps.repository.IssueRepository;

import javax.transaction.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class IssueService {
    private final IssueRepository issueRepository;
    private final AnswerRepository answerRepository;

    public IssueDto createIssue(CreateIssueDto issue, UserEntity user) {
        IssueEntity issueEntity = new IssueEntity(issue.getTitle(), issue.getDescription(), user);
        return IssueMapper.toDto(issueRepository.save(issueEntity), new ArrayList<>());
    }

    @Transactional
    public IssueDto getIssueById(Long issueId) {
        IssueEntity issue = getIssueEntityById(issueId);
        return IssueMapper.toDto(issue, answerRepository.findByIssueId(issueId));
    }

    public void deleteIssueById(Long issueId) {
        BitronixTransactionManager btm = TransactionManagerServices.getTransactionManager();
        try {
            btm.begin();
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

    public List<IssueDto> getAll() {
        return IssueMapper.toDto(issueRepository.findAll());
    }

    protected IssueEntity getIssueEntityById(Long issueId) {
        return issueRepository.findById(issueId)
                .orElseThrow(() -> new IssueNotFoundException(issueId.toString()));
    }

    public boolean isIssueOwner(Long issueId, UserEntity user) {
        return issueRepository.existsByIdAndAuthor(issueId, user);
    }
}
