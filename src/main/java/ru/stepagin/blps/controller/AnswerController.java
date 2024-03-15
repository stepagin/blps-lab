package ru.stepagin.blps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepagin.blps.entity.AnswerEntity;
import ru.stepagin.blps.service.AnswerService;

import java.util.List;

@RestController
@RequestMapping("/answer")
@CrossOrigin
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping("/{id}")
    public ResponseEntity<AnswerEntity> getAnswerById(@PathVariable Long id) {
        AnswerEntity answer = answerService.getAnswerById(id);
        return ResponseEntity.ok(answer);
    }

    @GetMapping("/issue/{issueId}")
    public ResponseEntity<List<AnswerEntity>> getAnswersByIssueId(@PathVariable Long issueId) {
        List<AnswerEntity> answers = answerService.getAnswersByIssueId(issueId);
        return ResponseEntity.ok(answers);
    }

    @PostMapping
    public ResponseEntity<AnswerEntity> createAnswer(@RequestBody AnswerEntity answer, @RequestParam Long issueId, @RequestParam Long authorId) {
        AnswerEntity createdAnswer = answerService.createAnswer(answer, issueId, authorId);
        return ResponseEntity.ok(createdAnswer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.ok("Answer deleted successfully");
    }
}

