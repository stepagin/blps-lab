package ru.stepagin.blps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepagin.blps.entity.IssueEntity;
import ru.stepagin.blps.service.SearchService;

import java.util.List;

@RestController
@RequestMapping("/search")
@CrossOrigin
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/issuesByTitle")
    public ResponseEntity<List<IssueEntity>> searchIssuesByTitle(@RequestParam String title) {
        List<IssueEntity> issues = searchService.searchIssuesByTitle(title);
        return ResponseEntity.ok(issues);
    }

    @GetMapping("/issuesByTags")
    public ResponseEntity<List<IssueEntity>> searchIssuesByTags(@RequestParam List<String> tags) {
        List<IssueEntity> issues = searchService.searchIssuesByTags(tags);
        return ResponseEntity.ok(issues);
    }
}

