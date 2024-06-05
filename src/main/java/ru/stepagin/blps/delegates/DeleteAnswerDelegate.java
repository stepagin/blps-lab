package ru.stepagin.blps.delegates;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.stepagin.blps.service.AnswerService;

@Component
public class DeleteAnswerDelegate implements JavaDelegate {

    private final AnswerService answerService;

    public DeleteAnswerDelegate(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        if (!delegateExecution.getProcessEngineServices().getIdentityService().getCurrentAuthentication().getGroupIds().contains("regular")) {
            throw new BpmnError("Not a regular group");
        }

        long issueId = (long) delegateExecution.getVariable("issue_id");
        long answerId = (long) delegateExecution.getVariable("answer_id");

        answerService.deleteAnswer(issueId, answerId);
    }
}
