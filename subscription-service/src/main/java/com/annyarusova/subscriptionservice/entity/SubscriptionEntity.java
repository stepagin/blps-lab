package com.annyarusova.subscriptionservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "subscription")
@Getter
@Setter
@RequiredArgsConstructor
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String tag;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", nullable = false)
    private LocalDateTime notifyInterval;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", nullable = false)
    private LocalDateTime lastNotify;

    public SubscriptionEntity(String email, String tag, LocalDateTime notifyInterval, LocalDateTime lastNotify) {
        this.email = email;
        this.tag = tag;
        this.lastNotify = lastNotify;
        this.notifyInterval = notifyInterval;
    }
}
