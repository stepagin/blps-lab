package ru.stepagin.blps.mapper;

import ru.stepagin.blps.dto.IssueDto;
import ru.stepagin.blps.entity.AnswerEntity;
import ru.stepagin.blps.entity.IssueEntity;
import ru.stepagin.blps.repository.IssueWithTagsInterface;

import java.util.ArrayList;
import java.util.List;

public abstract class IssueMapper {

    public static IssueDto toDto(IssueEntity issueEntity) {
        IssueDto issueDto = new IssueDto(issueEntity.getTitle(), issueEntity.getDescription());
        issueDto.setId(issueEntity.getId());
        issueDto.setDate(issueEntity.getDate());
        issueDto.setAuthor(PersonMapper.toDto(issueEntity.getAuthor()));
        issueDto.setAnswers(null);
        return issueDto;
    }

    public static List<IssueDto> toDto(List<IssueEntity> issueEntities) {
        return issueEntities.stream().map(IssueMapper::toDto).toList();
    }

    public static IssueDto toDto(IssueEntity issueEntity, List<AnswerEntity> answerEntities) {
        IssueDto issueDto = IssueMapper.toDto(issueEntity);
        issueDto.setAnswers(AnswerMapper.toDto(answerEntities));
        return issueDto;
    }

    public static IssueDto toDto(IssueEntity issueEntity, List<AnswerEntity> answerEntities, List<String> tags) {
        IssueDto issueDto = toDto(issueEntity, answerEntities);
        issueDto.setTags(tags);
        return issueDto;
    }

    public static IssueDto toDtoFromInterface(IssueWithTagsInterface issueTags) {
        IssueDto issueDto = IssueMapper.toDto(issueTags.getIssue());
        if (issueTags.getTags() != null) {
            issueDto.setTags(issueTags.getTags());
        } else {
            issueDto.setTags(new ArrayList<>());
        }

        return issueDto;
    }

    public static List<IssueDto> toDtoFromInterface(List<IssueWithTagsInterface> issueTagsList) {
        return issueTagsList.stream().map(IssueMapper::toDtoFromInterface).toList();
    }

}
