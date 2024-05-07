package ru.stepagin.blps.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.stepagin.blps.dto.AnswerDto;
import ru.stepagin.blps.dto.IssueDto;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.service.AnswerService;
import ru.stepagin.blps.service.IssueService;

@RestController
@RequestMapping("${api.endpoints.base-url}/issues/{issueId}/answers")
@CrossOrigin
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final IssueService issueService;

    @Operation(description = "Показать ответы по id вопроса")
    @GetMapping
    public ResponseEntity<IssueDto> getAnswersByIssueId(@PathVariable Long issueId) {
        return ResponseEntity.ok(answerService.getAnswersByIssueId(issueId));
    }

    @Operation(description = "Создать ответ")
    @PostMapping
    public ResponseEntity<AnswerDto> createAnswer(@PathVariable Long issueId,
                                                  @RequestBody AnswerDto answer,
                                                  Authentication authentication) {
        // TODO: make it return Issue with this answer
        // TODO: заменить заглушку
        UserEntity user = (UserEntity) authentication.getPrincipal(); // заглушка

        return ResponseEntity.ok(answerService.createAnswer(answer, issueId, user));
    }

    @Operation(description = "Показать ответ по id")
    @GetMapping("/{id}")
    public ResponseEntity<AnswerDto> getAnswerById(@PathVariable Long issueId, @PathVariable Long id) {
        return ResponseEntity.ok(answerService.getAnswerById(id));
    }

    @Operation(description = "Удалить ответ")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable Long issueId,
                                               @PathVariable Long id,
                                               Authentication authentication) {
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
}

