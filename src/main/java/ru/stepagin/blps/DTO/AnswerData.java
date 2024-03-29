package ru.stepagin.blps.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.stepagin.blps.entity.AnswerEntity;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class AnswerData {
    private Long id;
    private UserData author;
    private IssueData issue;
    @Size(max = 8000)
    @NotNull
    private String text;

    public AnswerData(AnswerEntity answer) {
        this.setId(answer.getId());
        this.setAuthor(new UserData(answer.getAuthor()));
        this.setIssue(new IssueData(answer.getIssue()));
        this.setText(answer.getText());
    }
}
