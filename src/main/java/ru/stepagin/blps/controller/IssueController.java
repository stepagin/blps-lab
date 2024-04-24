package ru.stepagin.blps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepagin.blps.DTO.IssueData;
import ru.stepagin.blps.service.IssueService;

import java.util.List;

@RestController
@RequestMapping("/api/issue")
@CrossOrigin
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping()
    public ResponseEntity<?> createIssue(@RequestBody IssueData issue, @RequestParam Long authorId) {
        return ResponseEntity.ok(issueService.createIssue(issue, authorId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIssueById(@PathVariable Long id) {
        issueService.deleteIssueById(id);
        return ResponseEntity.ok("Issue deleted successfully");
    }

    @GetMapping
    public ResponseEntity<?> getAllIssues() {
        List<IssueData> issueDataList = issueService.getAll();
        return ResponseEntity.ok(issueDataList);

    }
}

