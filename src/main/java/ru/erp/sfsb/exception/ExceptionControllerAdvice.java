package ru.erp.sfsb.exception;

import jakarta.validation.ConstraintViolationException;
import org.apache.poi.util.DocumentFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionData handleException(EntityNotFoundException e) {
        return new ExceptionData(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(EntityReferenceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionData handleException(EntityReferenceException e) {
        return new ExceptionData(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionData handleException(RuntimeException e) {
        return new ExceptionData(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(DocumentFormatException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionData handleException(DocumentFormatException e) {
        return new ExceptionData(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(EntityNullException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionData handleException(EntityNullException e) {
        return new ExceptionData(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ExceptionData> handleException(ConstraintViolationException e) {
        return e.getConstraintViolations().stream()
                .map(violation -> new ExceptionData(String.format("'%s' %s", violation.getPropertyPath().toString(), violation.getMessage())))
                .toList();
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ExceptionData> handleException(MethodArgumentNotValidException e) {
        return e.getBindingResult().getFieldErrors().stream()
                .map(error -> new ExceptionData(String.format("'%s' %s", error.getField(), error.getDefaultMessage())))
                .toList();
    }

    @ResponseBody
    @ExceptionHandler(ReportGenerateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionData handleException(ReportGenerateException e) {
        return new ExceptionData(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(KeycloakUserConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionData handleException(KeycloakUserConflictException e) {
        return new ExceptionData(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(EntityBlockException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionData handleException(EntityBlockException e) {
        return new ExceptionData(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(FileReadException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionData handleException(FileReadException e) {
        return new ExceptionData(e.getMessage());
    }
}