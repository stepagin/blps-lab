package ru.stepagin.blps.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.stepagin.blps.entity.IssueEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class IssueData {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime date;
    private UserData author;

    public IssueData(IssueEntity issue) {
        this.setId(issue.getId());
        this.setDate(issue.getDate());
        this.setTitle(issue.getTitle());
        this.setDescription(issue.getDescription());
        this.setAuthor(new UserData(issue.getAuthor()));
    }
}
