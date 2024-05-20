package com.annyarusova.subscriptionservice.repository;

import java.util.List;

public interface NotificationInterfaceDto {
    String getPerson();

    String getEmail();

    String getNickname();

    List<Long> getIssues();

    List<String> getTitles();
}
