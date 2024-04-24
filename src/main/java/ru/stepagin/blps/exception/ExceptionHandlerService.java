package ru.stepagin.blps.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerService {
    @ExceptionHandler(InvalidIdSuppliedException.class)
    public ResponseEntity<String> handleException(final InvalidIdSuppliedException e) {
        log.error("InvalidIdSuppliedException: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(IssueNotFoundException.class)
    public ResponseEntity<String> handleException(final IssueNotFoundException e) {
        log.error("TopicNotFoundException: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleException(final ValidationException e) {
        log.error("ValidationException: {}", e.getMessage());
        return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleException(final NoResourceFoundException e) {
        log.error("NoResourceFoundException: {}", e.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(final IllegalArgumentException e) {
        log.error("IllegalArgumentException: {}", e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<String> handleException(final UnsupportedOperationException e) {
        log.error("UnsupportedOperationException: {}", e.getMessage());
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(final RuntimeException e) {
        log.error("Runtime Exception ({}): {}", e.getClass(), e.getMessage());
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(final Exception e) {
        log.error("Common Exception ({}): {}", e.getClass(), e.getMessage());
        return ResponseEntity.internalServerError().body(e.getMessage());
    }


}
