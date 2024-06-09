package ru.stepagin.blps.delegates;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.stepagin.blps.dto.IssueDto;
import ru.stepagin.blps.service.IssueService;

@Component
public class GetIssueByIdDelegate implements JavaDelegate {

    private final IssueService issueService;

    public GetIssueByIdDelegate(IssueService issueService) {
        this.issueService = issueService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        long id = (long) delegateExecution.getVariable("issue_id");

        IssueDto issue = issueService.getIssueById(id);
        delegateExecution.setVariable("issue", issue);
        delegateExecution.setVariable("title", issue.getTitle());
        delegateExecution.setVariable("description", issue.getDescription());
        delegateExecution.setVariable("date", issue.getDate().toString());
        delegateExecution.setVariable("author", issue.getAuthor());
        delegateExecution.setVariable("answers", issue.getAnswers().toString());
        delegateExecution.setVariable("tags", issue.getTags().toString());

    }
}
