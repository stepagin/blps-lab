package ru.stepagin.blps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepagin.blps.DTO.IssueContext;
import ru.stepagin.blps.entity.IssueEntity;
import ru.stepagin.blps.service.IssueService;

@RestController
@RequestMapping("/issue")
@CrossOrigin
public class IssueController {

    @Autowired
    private IssueService issueService;

    @GetMapping("/{id}")
    public ResponseEntity<IssueContext> getIssueById(@PathVariable Long id) {
        try {
            IssueEntity issue = issueService.getIssueById(id);
            return ResponseEntity.ok(new IssueContext(issue));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new IssueContext(e.getMessage()));
        }

    }

    @PostMapping("/create")
    public ResponseEntity<IssueContext> createIssue(@RequestBody IssueEntity issue, @RequestParam Long authorId) {
        try {
            IssueEntity createdIssue = issueService.createIssue(issue, authorId);
            return ResponseEntity.ok(new IssueContext(createdIssue));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new IssueContext(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIssueById(@PathVariable Long id) {
        try {
            issueService.deleteIssueById(id);
            return ResponseEntity.ok("Issue deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

