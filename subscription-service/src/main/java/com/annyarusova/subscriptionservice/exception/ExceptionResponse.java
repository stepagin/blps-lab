package com.annyarusova.subscriptionservice.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Getter
@Setter
public class ExceptionResponse extends ProblemDetail {
    private String message;
    private int code;
    private String timestamp;
    private String id;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ExceptionResponse(String message, int code) {
        this.message = message;
        this.code = code;
        this.timestamp = LocalDateTime.now().format(formatter);
        // создаётся уникальный айди, по которому потом можно найти ошибку в логах
        this.id = UUID.randomUUID().toString();
    }

    public static ExceptionResponse forbidden(String message) {
        return new ExceptionResponse(message, HttpStatus.FORBIDDEN.value());
    }

    public static ExceptionResponse badRequest(String message) {
        return new ExceptionResponse(message, HttpStatus.BAD_REQUEST.value());
    }

    public static ExceptionResponse internalServerError(String message) {
        return new ExceptionResponse(message, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
