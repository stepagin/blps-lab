package ru.stepagin.blps.exception;

public class IssueNotFoundException extends InvalidIdSuppliedException {
    public IssueNotFoundException(final String message) {
        super(message);
    }
}
