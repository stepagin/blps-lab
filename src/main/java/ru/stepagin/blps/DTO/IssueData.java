package ru.stepagin.blps.DTO;

import jakarta.validation.constraints.Size;
import lombok.*;
import ru.stepagin.blps.entity.IssueEntity;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class IssueData {
    private Long id;
    @Size(max = 255)
    @NonNull
    private String title;
    @Size(max = 8000)
    @NonNull
    private String description;
    private LocalDateTime date;
    private UserData author;
    private List<AnswerData> answers;

    public IssueData(IssueEntity issue) {
        this.setId(issue.getId());
        this.setDate(issue.getDate());
        this.setTitle(issue.getTitle());
        this.setDescription(issue.getDescription());
        this.setAuthor(new UserData(issue.getAuthor()));
        this.setAnswers(null);
    }

    public IssueData(IssueData issue, List<AnswerData> answers) {
        this.setId(issue.getId());
        this.setDate(issue.getDate());
        this.setTitle(issue.getTitle());
        this.setDescription(issue.getDescription());
        this.setAuthor(issue.getAuthor());
        this.setAnswers(answers);
    }
}
