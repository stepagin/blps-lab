package com.annyarusova.subscriptionservice.repository;

import com.annyarusova.subscriptionservice.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
}
