package ru.stepagin.blps;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.stepagin.blps.controller.AnswerController;
import ru.stepagin.blps.entity.AnswerEntity;
import ru.stepagin.blps.service.AnswerService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnswerControllerTest {

    @Mock
    private AnswerService answerService;

    @InjectMocks
    private AnswerController answerController;

    @Test
    public void testGetAnswerById() {
        AnswerEntity mockedAnswer = new AnswerEntity();
        when(answerService.getAnswerById(anyLong())).thenReturn(mockedAnswer);

        ResponseEntity<AnswerEntity> response = answerController.getAnswerById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockedAnswer, response.getBody());
    }

    @Test
    public void testCreateAnswer() {
        AnswerEntity answerToCreate = new AnswerEntity();
        AnswerEntity createdAnswer = new AnswerEntity();
        when(answerService.createAnswer(any(AnswerEntity.class), anyLong(), anyLong())).thenReturn(createdAnswer);

        ResponseEntity<AnswerEntity> response = answerController.createAnswer(answerToCreate, 1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createdAnswer, response.getBody());
    }
}

