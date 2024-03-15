package ru.stepagin.blps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepagin.blps.entity.TagEntity;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
}
