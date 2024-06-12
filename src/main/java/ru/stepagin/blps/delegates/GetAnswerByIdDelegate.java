package ru.stepagin.blps.delegates;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.stepagin.blps.dto.AnswerDto;
import ru.stepagin.blps.dto.IssueDto;
import ru.stepagin.blps.service.AnswerService;

@Component
public class GetAnswerByIdDelegate implements JavaDelegate {

    private final AnswerService answerService;

    public GetAnswerByIdDelegate(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        long id = Long.parseLong(String.valueOf(delegateExecution.getVariable("issue_id")));
        AnswerDto answer = answerService.getAnswerById(id);
        delegateExecution.setVariable("answer", answer);
        delegateExecution.setVariable("author", answer.getAuthor());
        delegateExecution.setVariable("text", answer.getText());
    }
}
