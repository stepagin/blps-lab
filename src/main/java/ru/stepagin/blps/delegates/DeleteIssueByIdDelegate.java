package ru.stepagin.blps.delegates;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.stepagin.blps.service.IssueService;

@Component
public class DeleteIssueByIdDelegate implements JavaDelegate {
    private final IssueService issueService;

    public DeleteIssueByIdDelegate(IssueService issueService) {
        this.issueService = issueService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        if (!delegateExecution.getProcessEngineServices().getIdentityService().getCurrentAuthentication().getGroupIds().contains("regular")) {
            throw new BpmnError("Not a regular group");
        }

        long id = (long) delegateExecution.getVariable("issue_id");
        issueService.deleteIssueById(id);
    }
}
