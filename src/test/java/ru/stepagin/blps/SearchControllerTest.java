package ru.stepagin.blps;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.stepagin.blps.controller.SearchController;
import ru.stepagin.blps.entity.IssueEntity;
import ru.stepagin.blps.service.SearchService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchControllerTest {

    @Mock
    private SearchService searchService;

    @InjectMocks
    private SearchController searchController;

    @Test
    public void testSearchIssuesByTitle() {
        List<IssueEntity> mockedIssues = new ArrayList<>();
        when(searchService.searchIssuesByTitle(anyString())).thenReturn(mockedIssues);

        ResponseEntity<List<IssueEntity>> response = searchController.searchIssuesByTitle("test");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockedIssues, response.getBody());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSearchIssuesByTags() {
        List<IssueEntity> mockedIssues = new ArrayList<>();
        when(searchService.searchIssuesByTags(any(List.class))).thenReturn(mockedIssues);

        ResponseEntity<List<IssueEntity>> response = searchController.searchIssuesByTags(List.of("tag1"));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockedIssues, response.getBody());
    }
}

