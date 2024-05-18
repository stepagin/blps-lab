package com.annyarusova.subscriptionservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class IssueInfo {
    private Long id;
    private String title;
    private List<String> tags = new ArrayList<>();
    private LocalDateTime date;
}
