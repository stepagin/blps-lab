package ru.stepagin.blps.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "answer")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "issue_id", nullable = false)
    private IssueEntity issue;

    @Column(nullable = false)
    @Size(max = 8000)
    private String text;
}
