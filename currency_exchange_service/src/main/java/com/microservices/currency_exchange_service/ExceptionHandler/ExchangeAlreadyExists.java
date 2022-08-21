package com.microservices.currency_exchange_service.ExceptionHandler;

public class ExchangeAlreadyExists extends RuntimeException {
    public ExchangeAlreadyExists(String message) {
        super(message);
    }
}
