package ru.stepagin.blps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepagin.blps.entity.IssueEntity;
import ru.stepagin.blps.service.IssueService;

@RestController
@RequestMapping("/issue")
@CrossOrigin
public class IssueController {

    @Autowired
    private IssueService issueService;

    @GetMapping("/{id}")
    public ResponseEntity<IssueEntity> getIssueById(@PathVariable Long id) {
        IssueEntity issue = issueService.getIssueById(id);
        return ResponseEntity.ok(issue);
    }

    @PostMapping("/create")
    public ResponseEntity<IssueEntity> createIssue(@RequestBody IssueEntity issue, @RequestParam Long authorId) {
        IssueEntity createdIssue = issueService.createIssue(issue, authorId);
        return ResponseEntity.ok(createdIssue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIssueById(@PathVariable Long id) {
        issueService.deleteIssueById(id);
        return ResponseEntity.ok("Issue deleted successfully");
    }
}

