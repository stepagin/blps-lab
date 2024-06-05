package ru.stepagin.blps.delegates;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.stepagin.blps.dto.CreateAnswerDto;
import ru.stepagin.blps.dto.IssueDto;
import ru.stepagin.blps.service.AnswerService;

@Component
public class CreateAnswerDelegate implements JavaDelegate {

    private final AnswerService answerService;

    public CreateAnswerDelegate(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) {
        if (!delegateExecution.getProcessEngineServices().getIdentityService().getCurrentAuthentication().getGroupIds().contains("regular")) {
            throw new BpmnError("Not a regular group");
        }

        long issueId = (long) delegateExecution.getVariable("issue_id");
        String user = (String) delegateExecution.getVariable("user");
        CreateAnswerDto answer = (CreateAnswerDto) delegateExecution.getVariable("answer");
        IssueDto issueDto = answerService.createAnswer(answer, issueId, user);
        delegateExecution.setVariable("issueDto", issueDto);
    }
}
