package ru.stepagin.blps.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stepagin.blps.dto.CreateIssueDto;
import ru.stepagin.blps.dto.IssueDto;
import ru.stepagin.blps.entity.UserEntity;
import ru.stepagin.blps.security.SecurityService;
import ru.stepagin.blps.service.AuthService;
import ru.stepagin.blps.service.IssueService;

import java.util.List;

@RestController
@RequestMapping("${api.endpoints.base-url}/issues")
@CrossOrigin
@RequiredArgsConstructor
public class IssueController {
    private final IssueService issueService;
    private final AuthService authService;

    @Operation(description = "Создать вопрос")
    @PostMapping
    public ResponseEntity<IssueDto> createIssue(@RequestBody @Validated CreateIssueDto issue) {
        UserEntity user = authService.getAuthenticatedUser();
        return ResponseEntity.ok(issueService.createIssue(issue, user));
    }

    @Operation(description = "Показать все вопросы")
    @GetMapping
    public ResponseEntity<List<IssueDto>> getAllIssues() {
        List<IssueDto> issueDtoList = issueService.getAll();
        return ResponseEntity.ok(issueDtoList);
    }

    @Operation(description = "Показать вопрос")
    @GetMapping("/{id}")
    public ResponseEntity<IssueDto> getIssueById(@PathVariable Long id) {
        return ResponseEntity.ok(issueService.getIssueById(id));
    }

    @Operation(description = "Удалить вопрос")
    @DeleteMapping("/{id}")
    @PreAuthorize("@securityService.canEditIssue(#id)")
    public ResponseEntity<String> deleteIssueById(@PathVariable Long id) {
        issueService.deleteIssueById(id);
        return ResponseEntity.ok("Issue deleted successfully");
    }
}

