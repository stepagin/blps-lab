package ru.stepagin.blps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepagin.blps.DTO.IssueData;
import ru.stepagin.blps.DTO.ManyIssuesContext;
import ru.stepagin.blps.service.SearchService;

import java.util.List;

@RestController
@RequestMapping("/search")
@CrossOrigin
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/issuesByTitle")
    public ResponseEntity<ManyIssuesContext> searchIssuesByTitle(@RequestParam String title) {
        try {
            List<IssueData> issues = searchService.searchIssuesByTitle(title);
            return ResponseEntity.ok(new ManyIssuesContext(issues));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ManyIssuesContext(e.getMessage()));
        }
    }

    @GetMapping("/issuesByTags")
    public ResponseEntity<ManyIssuesContext> searchIssuesByTags(@RequestParam List<String> tags) {
        try {
            List<IssueData> issues = searchService.searchIssuesByTags(tags);
            return ResponseEntity.ok(new ManyIssuesContext(issues));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ManyIssuesContext(e.getMessage()));
        }
    }
}

