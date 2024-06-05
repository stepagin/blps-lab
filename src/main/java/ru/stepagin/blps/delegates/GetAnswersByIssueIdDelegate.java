package ru.stepagin.blps.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.stepagin.blps.dto.AnswerDto;
import ru.stepagin.blps.service.AnswerService;

import java.util.List;

@Component
public class GetAnswersByIssueIdDelegate implements JavaDelegate {
    private final AnswerService answerService;

    public GetAnswersByIssueIdDelegate(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) {
        long issueId = (long) delegateExecution.getVariable("issue_id");
        List<AnswerDto> answers = answerService.getAnswersByIssueId(issueId);
        delegateExecution.setVariable("answers", answers);
    }
}
