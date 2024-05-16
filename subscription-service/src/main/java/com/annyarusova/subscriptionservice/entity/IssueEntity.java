package com.annyarusova.subscriptionservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "issue")
@Getter
@Setter
@RequiredArgsConstructor
public class IssueEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String tag;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", nullable = false)
    private LocalDateTime creationDate;

    public IssueEntity(Long id, String tag, LocalDateTime creationDate) {
        this.id = id;
        this.tag = tag;
        this.creationDate = creationDate;
    }
}
