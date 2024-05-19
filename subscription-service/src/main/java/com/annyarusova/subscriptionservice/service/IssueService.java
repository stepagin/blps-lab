package com.annyarusova.subscriptionservice.service;

import com.annyarusova.subscriptionservice.entity.IssueEntity;
import com.annyarusova.subscriptionservice.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IssueService {
    private final IssueRepository issueRepository;

    @Transactional
    public void save(IssueEntity issueEntity) {
        issueRepository.save(issueEntity);
    }
}
