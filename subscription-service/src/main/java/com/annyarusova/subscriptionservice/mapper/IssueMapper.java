package com.annyarusova.subscriptionservice.mapper;

import com.annyarusova.subscriptionservice.dto.IssueInfo;
import com.annyarusova.subscriptionservice.entity.IssueEntity;

public abstract class IssueMapper {

    public static IssueEntity toEntity(final IssueInfo issueInfo) {
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setId(issueInfo.getId());
        issueEntity.setTitle(issueInfo.getTitle());
        issueEntity.setTags(issueInfo.getTags());
        issueEntity.setCreationDate(issueInfo.getDate());
        return issueEntity;
    }
}
