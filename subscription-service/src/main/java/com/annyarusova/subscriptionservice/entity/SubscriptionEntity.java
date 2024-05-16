package com.annyarusova.subscriptionservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.Period;

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
    private Period notifyInterval;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", nullable = false)
    private LocalDateTime lastNotify = LocalDateTime.now();

    public SubscriptionEntity(String email, String tag, int notifyInterval) {
        this.email = email;
        this.tag = tag;
        this.notifyInterval = Period.ofDays(notifyInterval);
    }
}
