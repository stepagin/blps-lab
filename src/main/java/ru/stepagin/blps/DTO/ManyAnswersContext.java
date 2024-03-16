package ru.stepagin.blps.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.stepagin.blps.entity.AnswerEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ManyAnswersContext {
    private boolean success;
    private List<AnswerData> answers;
    private String error;

    public ManyAnswersContext() {
        this.setSuccess(false);
        this.setError("empty many answers context");
        this.setAnswers(new ArrayList<>());
    }

    public ManyAnswersContext(List<AnswerEntity> answerEntityList) {
        List<AnswerData> answerDataList = new ArrayList<>();
        for (AnswerEntity ae : answerEntityList) {
            answerDataList.add(new AnswerData(ae));
        }
        this.setSuccess(true);
        this.setError("");
        this.setAnswers(answerDataList);
    }

    public ManyAnswersContext(String error) {
        this.setSuccess(false);
        this.setError(error);
        this.setAnswers(new ArrayList<>());
    }

}
