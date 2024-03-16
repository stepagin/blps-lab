package ru.stepagin.blps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepagin.blps.DTO.AnswerContext;
import ru.stepagin.blps.DTO.ManyAnswersContext;
import ru.stepagin.blps.entity.AnswerEntity;
import ru.stepagin.blps.service.AnswerService;

@RestController
@RequestMapping("/answer")
@CrossOrigin
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping("/{id}")
    public ResponseEntity<AnswerContext> getAnswerById(@PathVariable Long id) {
        try {
            AnswerEntity answer = answerService.getAnswerById(id);
            return ResponseEntity.ok(new AnswerContext(answer));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new AnswerContext(e.getMessage()));
        }
    }

    @GetMapping("/issue/{issueId}")
    public ResponseEntity<ManyAnswersContext> getAnswersByIssueId(@PathVariable Long issueId) {
        try {
            ManyAnswersContext answers = answerService.getAnswersByIssueId(issueId);
            return ResponseEntity.ok(answers);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ManyAnswersContext(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<AnswerContext> createAnswer(@RequestBody AnswerEntity answer, @RequestParam Long issueId, @RequestParam Long authorId) {
        try {
            AnswerContext createdAnswer = answerService.createAnswer(answer, issueId, authorId);
            return ResponseEntity.ok(createdAnswer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new AnswerContext(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable Long id) {
        try {
            answerService.deleteAnswer(id);
            return ResponseEntity.ok("Answer deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

