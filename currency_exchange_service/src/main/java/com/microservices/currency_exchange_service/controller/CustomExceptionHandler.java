package com.microservices.currency_exchange_service.controller;

import com.microservices.currency_exchange_service.ExceptionHandler.ExceptionBody;
import com.microservices.currency_exchange_service.ExceptionHandler.ExchangeAlreadyExists;
import com.microservices.currency_exchange_service.ExceptionHandler.ExchangeNotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExchangeNotFound.class)
    public final ResponseEntity<Object> handleExchangeNotFound(ExchangeNotFound exception , WebRequest request) {
        ExceptionBody exceptionBody = new ExceptionBody(exception.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionBody);
    }
    @ExceptionHandler(ExchangeAlreadyExists.class)
    public final ResponseEntity<Object> handleExchangeAlreadyExists(ExchangeAlreadyExists exception , WebRequest request) {
        ExceptionBody exceptionBody = new ExceptionBody(exception.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionBody);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExchange(ExchangeNotFound exception , WebRequest request) {
        ExceptionBody exceptionBody = new ExceptionBody(exception.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionBody);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionBody exceptionBody = new ExceptionBody("Argument Not Valid", request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionBody);
    }
}
