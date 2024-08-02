package ru.erp.sfsb.exception;

public class UniqueDataException extends RuntimeException {
    public UniqueDataException(String message) {
        super(message);
    }
}