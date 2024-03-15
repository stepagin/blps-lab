package ru.stepagin.blps;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.stepagin.blps.controller.IssueController;
import ru.stepagin.blps.entity.IssueEntity;
import ru.stepagin.blps.service.IssueService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IssueControllerTest {

    @Mock
    private IssueService issueService;

    @InjectMocks
    private IssueController issueController;

    @Test
    public void testGetIssueById() {
        IssueEntity mockedIssue = new IssueEntity();
        when(issueService.getIssueById(anyLong())).thenReturn(mockedIssue);

        ResponseEntity<IssueEntity> response = issueController.getIssueById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockedIssue, response.getBody());
    }

    @Test
    public void testCreateIssue() {
        IssueEntity issueToCreate = new IssueEntity();
        IssueEntity createdIssue = new IssueEntity();
        when(issueService.createIssue(any(IssueEntity.class), anyLong())).thenReturn(createdIssue);

        ResponseEntity<IssueEntity> response = issueController.createIssue(issueToCreate, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createdIssue, response.getBody());
    }
}

