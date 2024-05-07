package ru.stepagin.blps.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepagin.blps.dto.IssueDto;
import ru.stepagin.blps.service.SearchService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${api.endpoints.base-url}/questions") // TODO: переместить в обычный get: /issues с пагинацией
@CrossOrigin
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @Operation(description = "Производит поиск по названию и тегам")
    @GetMapping
    public ResponseEntity<List<IssueDto>> searchIssuesByTitle(@RequestParam(name = "title") String title,
                                                              @RequestParam(name = "tags") List<String> tags) {

//        List<IssueDto> issues = searchService.search(title, tags);
//        return ResponseEntity.ok(issues);
        return ResponseEntity.ok(new ArrayList<>());
    }
}

