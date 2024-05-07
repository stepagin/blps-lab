package ru.stepagin.blps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stepagin.blps.entity.IssueTagEntity;

public interface IssueTagRepository extends JpaRepository<IssueTagEntity, Long> {
//    @Query("select i from IssueTagEntity i where i.tag.name in :names order by i.issue.id DESC")
//    List<IssueTagEntity> findByTagNames(@Param("names") List<String> tagNames);
//
//    @Query("select i from IssueTagEntity i where upper(i.tag.name) = upper(:name) order by i.issue.id DESC")
//    List<IssueTagEntity> findByTagName(@Param("name") String name);
//
//    @Query("""
//            select i from IssueTagEntity i
//            where i.tag.name in :names and upper(i.issue.title) like upper(concat('%', :title, '%'))""")
//    List<IssueTagEntity> findByTitleAndTags(@Param("title") String title, @Param("names") List<String> tagNames);


}
