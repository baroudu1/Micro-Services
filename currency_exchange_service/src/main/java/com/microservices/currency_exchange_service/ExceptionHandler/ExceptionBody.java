package com.microservices.currency_exchange_service.ExceptionHandler;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @NoArgsConstructor
@ToString
public class ExceptionBody {
    private final Date timestamp = new Date();
    private String message;
    private String details;

    public ExceptionBody(String message, String details) {
        this.message = message;
        this.details = details;
    }
}
