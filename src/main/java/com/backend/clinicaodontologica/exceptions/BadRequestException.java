package com.backend.clinicaodontologica.exceptions;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


public class BadRequestException extends Exception {
    public BadRequestException(String message) {

        super(message);
    }
}
