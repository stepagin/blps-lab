package ru.stepagin.blps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stepagin.blps.entity.IssueEntity;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<IssueEntity, Long> {
    List<IssueEntity> findByTitleContainingIgnoreCase(String title);
}

