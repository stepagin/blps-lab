package ru.stepagin.blps.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.stepagin.blps.entity.AnswerEntity;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class AnswerData {
    private Long id;
    private UserData author;
    private IssueData issue;
    private String text;

    public AnswerData(AnswerEntity answer) {
        this.setId(answer.getId());
        this.setAuthor(new UserData(answer.getAuthor()));
        this.setIssue(new IssueData(answer.getIssue()));
        this.setText(answer.getText());
    }
}
