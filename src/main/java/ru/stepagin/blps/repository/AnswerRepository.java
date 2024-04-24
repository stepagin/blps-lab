package ru.stepagin.blps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepagin.blps.entity.AnswerEntity;

import java.util.List;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
    List<AnswerEntity> findByIssue_Id(Long id);
}
