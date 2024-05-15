package ru.stepagin.blps.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "issue_tag",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"issue_id", "tag_id"})
        })
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class IssueTagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "issue_id", nullable = false)
    private IssueEntity issue;

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private TagEntity tag;

    public IssueTagEntity(IssueEntity issue, TagEntity tag) {
        this.issue = issue;
        this.tag = tag;
    }
}
