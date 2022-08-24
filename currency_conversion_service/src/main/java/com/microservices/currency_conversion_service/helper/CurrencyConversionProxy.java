package com.microservices.currency_conversion_service.helper;


import com.microservices.currency_conversion_service.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange-service")
public interface CurrencyConversionProxy {
    @GetMapping("/currency-exchanges/from/{from}/to/{to}")
    CurrencyConversion getCurrencyConversion(@PathVariable String from, @PathVariable String to);
}
