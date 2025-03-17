package com.mathias.jabuti.domain.exception;

public enum MessageType {

    ENITTY_NOT_FOUND("%s with id %d not found"),
    DUPLICATED_ENTITY("%s with id %d already exists");

    private final String message;

    MessageType(String message) {
        this.message = message;
    }

    public String format(Object... args) {
        return String.format(message, args);
    }
}
