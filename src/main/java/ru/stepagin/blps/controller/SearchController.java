package ru.stepagin.blps.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepagin.blps.service.SearchService;

import java.util.List;

@RestController
@RequestMapping("${api.endpoints.base-url}/issues") // TODO: переместить в обычный get: /issues с пагинацией
@CrossOrigin
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @Operation(description = "Показать вопросы")
    @GetMapping
    public ResponseEntity<?> getAllIssuesFiltered(@RequestParam(name = "title", required = false, defaultValue = "") String title,
                                                  @RequestParam(name = "tags", required = false) List<String> tags,
                                                  @RequestParam(name = "size", required = false, defaultValue = "20") int pageSize,
                                                  @RequestParam(name = "page", required = false, defaultValue = "0") int page) {
        return ResponseEntity.ok(searchService.getAll(title, tags, PageRequest.of(page, pageSize)));
    }
}

