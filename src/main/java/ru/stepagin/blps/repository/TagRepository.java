package ru.stepagin.blps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.stepagin.blps.entity.TagEntity;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

    boolean existsByName(String name);

    @Query("select t from TagEntity t where upper(t.name) = upper(:name)")
    TagEntity findTagByName(@Param("name") String name);
}
