package ru.stepagin.blps.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "issue")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class IssueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String description;

    @CreationTimestamp
    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "author_id", updatable = false, nullable = false)
    private UserEntity author;

    public IssueEntity(String title, String description, UserEntity author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }
}
