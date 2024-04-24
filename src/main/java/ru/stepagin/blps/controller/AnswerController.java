package ru.stepagin.blps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepagin.blps.DTO.AnswerData;
import ru.stepagin.blps.DTO.IssueData;
import ru.stepagin.blps.entity.AnswerEntity;
import ru.stepagin.blps.service.AnswerService;
import ru.stepagin.blps.service.IssueService;

@RestController
@RequestMapping("/api/issue/{issueId}")
@CrossOrigin
public class AnswerController {
    @Autowired
    private AnswerService answerService;
    @Autowired
    private IssueService issueService;

    @GetMapping
    public ResponseEntity<IssueData> getAnswersByIssueId(@PathVariable Long issueId) {
        IssueData result = answerService.getAnswersByIssueId(issueId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/answers")
    public ResponseEntity<?> createAnswer(@PathVariable Long issueId, @RequestBody AnswerEntity answer, @RequestParam Long authorId) {
        // TODO: get user by Authorization context (и в других запросах тоже)
        AnswerData createdAnswer = answerService.createAnswer(answer, issueId, authorId);
        return ResponseEntity.ok(createdAnswer);
    }

    @GetMapping("/answers/{id}")
    public ResponseEntity<?> getAnswerById(@PathVariable Long issueId, @PathVariable Long id) {
        AnswerData answer = answerService.getAnswerById(id);
        return ResponseEntity.ok(answer);
    }

    @DeleteMapping("/answers/{id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable Long issueId, @PathVariable Long id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.ok("Answer deleted successfully");
    }
}

