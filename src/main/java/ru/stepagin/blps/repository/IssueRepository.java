package ru.stepagin.blps.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.stepagin.blps.entity.IssueEntity;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<IssueEntity, Long> {
    @Query("select (count(i) > 0) from IssueEntity i where i.id = :id and i.author = :author")
    boolean existsByIdAndAuthor(@Param("id") @NonNull Long id, @Param("author") @NonNull UserEntity author);

    @Query("select i as issue, " +
            "(select array_agg (t.tag.name) within group (order by t.tag.name) from IssueTagEntity t where t.issue.id = i.id) as tags " +
            "from IssueEntity i")
    List<IssueWithTagsInterface> findAllWithTags(PageRequest pageRequest);

    @Query("select i as issue, array_agg (it.tag.name) within group (order by it.tag.name) as tags " +
            "from IssueEntity i " +
            "join IssueTagEntity it on i.id = it.issue.id " +
            "where upper(i.title) like upper(concat('%', :title, '%')) " +
            "group by i.id")
    List<IssueWithTagsInterface> findByTitle(@Param("title") @NonNull String title, Pageable pageable);

}

