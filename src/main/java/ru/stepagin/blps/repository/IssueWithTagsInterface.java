package ru.stepagin.blps.repository;

import ru.stepagin.blps.entity.IssueEntity;

import java.util.List;

public interface IssueWithTagsInterface {
    IssueEntity getIssue();

    List<String> getTags();
}
