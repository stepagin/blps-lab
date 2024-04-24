package ru.stepagin.blps.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepagin.blps.DTO.IssueData;
import ru.stepagin.blps.service.SearchService;

import java.util.List;

@RestController
@RequestMapping("/api/questions/search")
@CrossOrigin
@RequiredArgsConstructor
public class SearchController {

    @Autowired
    private final SearchService searchService;

    @GetMapping("/by-title")
    public ResponseEntity<?> searchIssuesByTitle(@RequestParam String title) {
        List<IssueData> issues = searchService.searchIssuesByTitle(title);
        return ResponseEntity.ok(issues);
    }

    @GetMapping("/by-tags")
    public ResponseEntity<?> searchIssuesByTags(@RequestParam List<String> tags) {
        List<IssueData> issues = searchService.searchIssuesByTags(tags);
        return ResponseEntity.ok(issues);
    }
}

