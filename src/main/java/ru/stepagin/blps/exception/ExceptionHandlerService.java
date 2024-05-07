package ru.stepagin.blps.exception;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.Objects;

@Slf4j
@ControllerAdvice
@Hidden
public class ExceptionHandlerService {

    @ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Пользователь не авторизован")
    @ExceptionHandler(AccessDeniedException.class)
    public ErrorResponse handleException(final AccessDeniedException e) {
        log.error("AccessDeniedException: {}", e.getMessage());
        // TODO: сделать собственную DTO для ошибок и поставить её везде
        return ErrorResponse.builder(e, HttpStatus.FORBIDDEN, e.getMessage()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleException(final MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: {}", e.getMessage());
        String responseMessage = Arrays.stream(Objects.requireNonNull(e.getDetailMessageArguments())).toList().get(1).toString();
        return ErrorResponse.builder(e, HttpStatus.BAD_REQUEST, responseMessage).build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorResponse handleException(final DataIntegrityViolationException e) {
        log.error("DataIntegrityViolationException: {}", e.getMessage());
        String responseMessage = e.getMessage();
        return ErrorResponse.builder(e, HttpStatus.INTERNAL_SERVER_ERROR, responseMessage).build();
    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<String> handleException(final MethodArgumentNotValidException e) {
//        log.error("MethodArgumentNotValidException: {}", e.getMessage());
//        return ResponseEntity.badRequest().body(e.getMessage());
//    }
//
//    @ExceptionHandler(InvalidIdSuppliedException.class)
//    public ResponseEntity<String> handleException(final InvalidIdSuppliedException e) {
//        log.error("InvalidIdSuppliedException: {}", e.getMessage());
//        return ResponseEntity.badRequest().body(e.getMessage());
//    }
//
//    @ExceptionHandler(IssueNotFoundException.class)
//    public ResponseEntity<String> handleException(final IssueNotFoundException e) {
//        log.error("TopicNotFoundException: {}", e.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//    }
//
//    @ExceptionHandler(ValidationException.class)
//    public ResponseEntity<String> handleException(final ValidationException e) {
//        log.error("ValidationException: {}", e.getMessage());
//        return ResponseEntity.unprocessableEntity().body(e.getMessage());
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handleException(final IllegalArgumentException e) {
//        log.error("IllegalArgumentException: {}", e.getMessage());
//        return ResponseEntity.badRequest().body(e.getMessage());
//    }
//
//    @ExceptionHandler(UnsupportedOperationException.class)
//    public ResponseEntity<String> handleException(final UnsupportedOperationException e) {
//        log.error("UnsupportedOperationException: {}", e.getMessage());
//        return ResponseEntity.internalServerError().body(e.getMessage());
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<String> handleException(final RuntimeException e) {
//        log.error("Runtime Exception ({}): {}", e.getClass(), e.getMessage());
//        return ResponseEntity.internalServerError().body(e.getMessage());
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(final Exception e) {
        log.error("Common Exception ({}): {}", e.getClass(), e.getMessage());
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

}
