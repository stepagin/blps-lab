package ru.stepagin.blps.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stepagin.blps.dto.AnswerDto;
import ru.stepagin.blps.dto.CreateAnswerDto;
import ru.stepagin.blps.dto.IssueDto;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.security.SecurityService;
import ru.stepagin.blps.service.AnswerService;

import java.util.List;

@RestController
@RequestMapping("${api.endpoints.base-url}/issues/{issueId}/answers")
@CrossOrigin
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final SecurityService securityService;

    @Operation(description = "Показать ответы по id вопроса")
    @GetMapping
    public ResponseEntity<List<AnswerDto>> getAnswersByIssueId(@PathVariable Long issueId) {
        return ResponseEntity.ok(answerService.getAnswersByIssueId(issueId));
    }

    @Operation(description = "Создать ответ")
    @PostMapping
    public ResponseEntity<IssueDto> createAnswer(@PathVariable Long issueId,
                                                 @RequestBody @Validated CreateAnswerDto answer,
                                                 Authentication authentication) {
        UserEntity user = securityService.getUser(authentication);
        return ResponseEntity.ok(answerService.createAnswer(answer, issueId, user));
    }

    @Operation(description = "Показать ответ по id")
    @GetMapping("/{id}")
    public ResponseEntity<AnswerDto> getAnswerById(@PathVariable Long issueId,
                                                   @PathVariable Long id) {
        return ResponseEntity.ok(answerService.getAnswerById(id));
    }

    @Operation(description = "Удалить ответ")
    @DeleteMapping("/{id}")
    @PreAuthorize("@securityService.isAnswerOwner(#id, authentication)")
    public ResponseEntity<String> deleteAnswer(@PathVariable Long issueId,
                                               @PathVariable Long id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
}

