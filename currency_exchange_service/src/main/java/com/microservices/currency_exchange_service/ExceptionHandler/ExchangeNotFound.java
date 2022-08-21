package com.microservices.currency_exchange_service.ExceptionHandler;

public class ExchangeNotFound extends RuntimeException {
    public ExchangeNotFound(String message) {
        super(message);
    }
}
