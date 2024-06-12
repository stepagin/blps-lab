package ru.stepagin.blps.delegates;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.stepagin.blps.dto.CreateIssueDto;
import ru.stepagin.blps.service.IssueService;
import ru.stepagin.blps.dto.IssueDto;

@Component
public class CreateIssueDelegate implements JavaDelegate {

    private final IssueService issueService;
    public CreateIssueDelegate(IssueService issueService) {
        this.issueService = issueService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String title = (String) delegateExecution.getVariable("title");
        String description = (String) delegateExecution.getVariable("description");
        String user = delegateExecution.getProcessEngineServices().getIdentityService().getCurrentAuthentication().getUserId();

        CreateIssueDto issue = new CreateIssueDto();
        issue.setTitle(title);
        issue.setDescription(description);
        issueService.createIssue(issue, user);
    }
}
