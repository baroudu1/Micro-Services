package com.microservices.currency_conversion_service.controller;

import com.microservices.currency_conversion_service.model.CurrencyConversion;
import com.microservices.currency_conversion_service.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-conversion")
public class CurrencyConversionController {
    @Autowired
    private CurrencyConversionService currencyConversionService;

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion getConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable int quantity) {
        return currencyConversionService.getCurrencyConversion(from, to, quantity);
    }
}
