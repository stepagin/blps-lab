package com.annyarusova.subscriptionservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subscription",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"person", "tag"})
        })
@Getter
@Setter
@RequiredArgsConstructor
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String tag;

    @Column(nullable = false)
    private int interval;

    @ManyToOne
    @JoinColumn(name = "person", nullable = false)
    private UserEntity user;

    public SubscriptionEntity(UserEntity user, String tag, int interval) {
        this.user = user;
        this.tag = tag;
        this.interval = interval;
    }
}
