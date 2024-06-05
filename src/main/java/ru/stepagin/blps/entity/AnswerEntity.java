package ru.stepagin.blps.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Length;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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

    @Column(name = "author_login", nullable = false)
    private String authorLogin;

    @ManyToOne
    @JoinColumn(name = "issue_id", nullable = false)
    private IssueEntity issue;

    @Column(nullable = false, length = Length.LONG)
    private String text;

    @CreationTimestamp
    @Column(name = "date", nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime date = LocalDateTime.now();

    public AnswerEntity(String text, String author, IssueEntity issue) {
        this.text = text;
        this.authorLogin = author;
        this.issue = issue;
    }
}
