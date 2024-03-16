package ru.stepagin.blps.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ManyIssuesContext {
    private boolean success;
    private List<IssueData> issues;
    private String error;

    public ManyIssuesContext() {
        this.setSuccess(false);
        this.setError("empty many answers context");
        this.setIssues(new ArrayList<>());
    }

    public ManyIssuesContext(List<IssueData> issueDataList) {
        this.setSuccess(true);
        this.setError("");
        this.setIssues(issueDataList);
    }

    public ManyIssuesContext(String error) {
        this.setSuccess(false);
        this.setError(error);
        this.setIssues(new ArrayList<>());
    }


}
