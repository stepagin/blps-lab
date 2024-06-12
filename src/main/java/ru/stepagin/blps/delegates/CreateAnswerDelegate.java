package ru.stepagin.blps.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import ru.stepagin.blps.dto.CreateAnswerDto;
import ru.stepagin.blps.service.AnswerService;

@Component
public class CreateAnswerDelegate implements JavaDelegate {

    private final AnswerService answerService;

    public CreateAnswerDelegate(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) {
        long id = Long.parseLong(String.valueOf(delegateExecution.getVariable("issue_id")));
        String text = (String) delegateExecution.getVariable("text");
        String user = delegateExecution.getProcessEngineServices().getIdentityService().getCurrentAuthentication().getUserId();

        CreateAnswerDto answer = new CreateAnswerDto();
        answer.setText(text);
        answerService.createAnswer(answer, id, user);
    }
}
