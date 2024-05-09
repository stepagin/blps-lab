package ru.stepagin.blps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.stepagin.blps.entity.IssueEntity;
import ru.stepagin.blps.entity.UserEntity;

@Repository
public interface IssueRepository extends JpaRepository<IssueEntity, Long> {
    @Query("select (count(i) > 0) from IssueEntity i where i.id = :id and i.author = :author")
    boolean existsByIdAndAuthor(@Param("id") @NonNull Long id, @Param("author") @NonNull UserEntity author);
}

