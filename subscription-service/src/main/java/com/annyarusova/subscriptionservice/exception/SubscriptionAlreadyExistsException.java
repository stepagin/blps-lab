package com.annyarusova.subscriptionservice.exception;

public class SubscriptionAlreadyExistsException extends IllegalArgumentException {
    public SubscriptionAlreadyExistsException(String message) {
        super(message);
    }
}
