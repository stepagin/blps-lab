package ru.stepagin.blps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.stepagin.blps.entity.AnswerEntity;
import ru.stepagin.blps.entity.UserEntity;

import java.util.List;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
    @Transactional
    List<AnswerEntity> findByIssueId(Long id);

    @Query("select (count(a) > 0) from AnswerEntity a where a.id = :id and a.author = :author")
    boolean existsByIdAndAuthor(@Param("id") Long id, @Param("author") UserEntity author);

    @Transactional
    @Modifying
    void deleteByIssueId(Long issueId);
}
