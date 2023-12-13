package ru.erp.sfsb.exception;

public class KeycloakUserConflictException extends RuntimeException {
    public KeycloakUserConflictException(String message) {
        super(message);
    }
}