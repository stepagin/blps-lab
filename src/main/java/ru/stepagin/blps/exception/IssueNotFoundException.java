package ru.stepagin.blps.exception;

public class IssueNotFoundException extends RuntimeException {
    public IssueNotFoundException(final String message) {
        super(message);
    }
}
