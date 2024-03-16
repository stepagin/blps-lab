package ru.stepagin.blps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepagin.blps.DTO.IssueContext;
import ru.stepagin.blps.DTO.IssueData;
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
            IssueData issue = issueService.getIssueById(id);
            return ResponseEntity.ok(new IssueContext(issue));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new IssueContext(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new IssueContext("Internal error while finding issue"));
        }

    }

    @PostMapping("/create")
    public ResponseEntity<IssueContext> createIssue(@RequestBody IssueData issue, @RequestParam Long authorId) {
        try {
            IssueData createdIssue = issueService.createIssue(issue, authorId);
            return ResponseEntity.ok(new IssueContext(createdIssue));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new IssueContext(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new IssueContext("Internal error while creating issue"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIssueById(@PathVariable Long id) {
        try {
            issueService.deleteIssueById(id);
            return ResponseEntity.ok("Issue deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Internal error while deleting issue");
        }
    }
}

