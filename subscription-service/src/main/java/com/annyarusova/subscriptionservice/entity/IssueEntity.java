package com.annyarusova.subscriptionservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "issue")
@Getter
@Setter
@RequiredArgsConstructor
public class IssueEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(nullable = false)
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> tags = new ArrayList<>();

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", nullable = false)
    private LocalDateTime creationDate;
}
