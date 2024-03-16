package ru.stepagin.blps.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.stepagin.blps.entity.AnswerEntity;

@Getter
@Setter
@ToString
public class AnswerContext {
    private boolean success;
    private AnswerData answer;
    private String error;

    public AnswerContext() {
        this.setSuccess(false);
        this.setError("empty answer context");
        this.setAnswer(null);
    }

    public AnswerContext(AnswerEntity answer) {
        this.setSuccess(true);
        this.setError("");
        this.setAnswer(new AnswerData(answer));
    }

    public AnswerContext(String error) {
        this.setSuccess(false);
        this.setError(error);
        this.setAnswer(null);
    }
}
