package ru.stepagin.blps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepagin.blps.entity.IssueTagEntity;

import java.util.List;

public interface IssueTagRepository extends JpaRepository<IssueTagEntity, Long> {
    List<IssueTagEntity> findByTag_Name(String name);

}
