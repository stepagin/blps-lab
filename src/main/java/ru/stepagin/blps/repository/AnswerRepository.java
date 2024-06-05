package ru.stepagin.blps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.stepagin.blps.entity.AnswerEntity;

import java.util.List;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
    @Transactional
    List<AnswerEntity> findByIssueId(Long id);

    @Query("select (count(a) > 0) from AnswerEntity a where a.id = :id and a.authorEmail = :authorEmail")
    boolean existsByIdAndAuthor(@Param("id") Long id, @Param("author") String authorEmail);

    @Transactional
    @Modifying
    void deleteByIssueId(Long issueId);
}
