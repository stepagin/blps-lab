package com.annyarusova.subscriptionservice.repository;

import com.annyarusova.subscriptionservice.entity.IssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<IssueEntity, Long> {
}
