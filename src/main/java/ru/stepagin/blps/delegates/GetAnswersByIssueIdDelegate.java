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
        long issueId = Long.parseLong(String.valueOf(delegateExecution.getVariable("issue_id")));
        List<AnswerDto> answers = answerService.getAnswersByIssueId(issueId);
        var answer = answers.get(0);
        delegateExecution.setVariable("answer", answer);
        delegateExecution.setVariable("author", answer.getAuthor());
        delegateExecution.setVariable("text", answer.getText());
    }
}
