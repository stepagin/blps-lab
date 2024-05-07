package ru.stepagin.blps.mapper;

import ru.stepagin.blps.dto.AnswerDto;
import ru.stepagin.blps.entity.AnswerEntity;

import java.util.List;

public abstract class AnswerMapper {

    public static AnswerDto toDto(AnswerEntity answerEntity) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setId(answerEntity.getId());
        answerDto.setText(answerEntity.getText());
        answerDto.setAuthor(answerEntity.getAuthor().getNickname());
        answerDto.setCreatedAt(answerEntity.getDate());
        return answerDto;
    }

    public static List<AnswerDto> toDto(List<AnswerEntity> answerEntities) {
        return answerEntities.stream().map(AnswerMapper::toDto).toList();
    }
}
