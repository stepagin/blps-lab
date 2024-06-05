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
        if (!delegateExecution.getProcessEngineServices().getIdentityService().getCurrentAuthentication().getGroupIds().contains("regular")) {
            throw new BpmnError("Not a regular group");
        }

        String user = (String) delegateExecution.getVariable("user");
        CreateIssueDto issue = (CreateIssueDto) delegateExecution.getVariable("issue");
        IssueDto issueDto = issueService.createIssue(issue, user);
        delegateExecution.setVariable("issueDto", issueDto);
    }
}
