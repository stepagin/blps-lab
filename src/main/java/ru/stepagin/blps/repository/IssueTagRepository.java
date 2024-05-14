package ru.stepagin.blps.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.stepagin.blps.entity.IssueTagEntity;
import ru.stepagin.blps.entity.TagEntity;

import java.util.List;

@Repository
public interface IssueTagRepository extends JpaRepository<IssueTagEntity, Long> {
    @Query("select " +
            "i.issue as issue, array_agg (i.tag.name) within group (order by i.tag.name) as tags " +
            "from IssueTagEntity i " +
            "group by i.issue " +
            "having any (i.tag.name in :tagNames)")
    List<IssueWithTagsInterface> findAllByTags(@Param("tagNames") List<String> tagNames, PageRequest pageRequest);

    @Transactional
    @Modifying
    @Query("delete from IssueTagEntity i where i.issue.id = :issueId")
    void deleteByIssueId(Long issueId);

    @Query("select " +
            "i.issue as issue, array_agg (i.tag.name) within group (order by i.tag.name) as tags " +
            "from IssueTagEntity i " +
            "group by i.issue " +
            "having upper(i.issue.title) like upper(concat('%', :title, '%')) " +
            "and any (i.tag.name in :tagNames)")
    List<IssueWithTagsInterface> findByTagsAndTitle(@Param("tagNames") List<String> tagNames, @Param("title") String title, Pageable pageable);

    @Query("select i.tag from IssueTagEntity i where i.issue.id = :id")
    List<TagEntity> findByIssueId(@Param("id") Long id);


}








