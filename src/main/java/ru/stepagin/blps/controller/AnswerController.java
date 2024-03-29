package ru.stepagin.blps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepagin.blps.DTO.AnswerData;
import ru.stepagin.blps.DTO.IssueData;
import ru.stepagin.blps.entity.AnswerEntity;
import ru.stepagin.blps.service.AnswerService;
import ru.stepagin.blps.service.IssueService;

import java.util.List;

@RestController
@RequestMapping("/api/issue/{issueId}")
@CrossOrigin
public class AnswerController {

    @Autowired
    private AnswerService answerService;
    @Autowired
    private IssueService issueService;

    @GetMapping
    public ResponseEntity<?> getAnswersByIssueId(@PathVariable Long issueId) {
        try {
            IssueData issue = issueService.getIssueById(issueId);
            List<AnswerData> answers = answerService.getAnswersByIssueId(issueId);
            return ResponseEntity.ok(new IssueData(issue, answers));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/answers")
    public ResponseEntity<?> createAnswer(@PathVariable Long issueId, @RequestBody AnswerEntity answer, @RequestParam Long authorId) {
        try {
            AnswerData createdAnswer = answerService.createAnswer(answer, issueId, authorId);
            return ResponseEntity.ok(createdAnswer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/answers/{id}")
    public ResponseEntity<?> getAnswerById(@PathVariable Long issueId, @PathVariable Long id) {
        try {
            AnswerEntity answer = answerService.getAnswerById(id);
            return ResponseEntity.ok(new AnswerData(answer));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/answers/{id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable Long issueId, @PathVariable Long id) {
        try {
            answerService.deleteAnswer(id);
            return ResponseEntity.ok("Answer deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

