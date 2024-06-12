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
        long issueId = Long.parseLong(String.valueOf(delegateExecution.getVariable("issue_id")));
        long answerId = Long.parseLong(String.valueOf(delegateExecution.getVariable("answer_id")));
        String user = delegateExecution.getProcessEngineServices().getIdentityService().getCurrentAuthentication().getUserId();
        if (!delegateExecution.getProcessEngineServices().getIdentityService().getCurrentAuthentication().getGroupIds().contains("moderator")
                && !answerService.isAnswerOwner(answerId, user)
        ) {
            throw new BpmnError("Not a moderator group or owner");
        }
        answerService.deleteAnswer(issueId, answerId);
    }
}
