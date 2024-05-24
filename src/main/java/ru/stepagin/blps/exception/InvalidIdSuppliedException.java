package ru.stepagin.blps.exception;

public class InvalidIdSuppliedException extends RuntimeException {
    public InvalidIdSuppliedException(final String message) {
        super(message);
    }
}
