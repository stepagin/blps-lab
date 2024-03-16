package ru.stepagin.blps.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.stepagin.blps.entity.IssueEntity;

@Getter
@Setter
@ToString
public class IssueContext {
    private boolean success;
    private IssueData issue;
    private String error;

    public IssueContext() {
        this.setSuccess(false);
        this.setIssue(null);
        this.setError("empty issue context");
    }

    public IssueContext(String error) {
        this.setSuccess(false);
        this.setIssue(null);
        this.setError(error);
    }

    public IssueContext(IssueEntity issue) {
        this.setSuccess(true);
        this.setError("");
        this.setIssue(new IssueData(issue));
    }
}
