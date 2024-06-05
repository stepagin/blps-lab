package ru.stepagin.blps.delegates;

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
        long id = (long) delegateExecution.getVariable("id");
        IssueDto issueDto = issueService.getIssueById(id);
        delegateExecution.setVariable("issueDto", issueDto);
    }
}
