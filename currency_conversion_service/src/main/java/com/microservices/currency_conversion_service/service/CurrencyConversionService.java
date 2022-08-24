package com.microservices.currency_conversion_service.service;

import com.microservices.currency_conversion_service.helper.CurrencyConversionProxy;
import com.microservices.currency_conversion_service.model.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionService {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyConversionProxy currencyConversionProxy;

    public CurrencyConversion getCurrencyConversion(String from, String to, int quantity) {
        String port = environment.getProperty("local.server.port");

//        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate()
//                .getForEntity("http://localhost:8000/currency-exchanges/from/{from}/to/{to}",
//                        CurrencyConversion.class, from, to);
//        CurrencyConversion currencyConversion = responseEntity.getBody();

        CurrencyConversion currencyConversion = null;
        try {
//            System.err.println(currencyConversionProxy.getCurrencyConversion(from, to).toString());
            currencyConversion = currencyConversionProxy.getCurrencyConversion(from, to);
            currencyConversion.setQuantity(quantity);
            currencyConversion.setTotalCalculatedAmount(quantity * currencyConversion.getConversionMultiple());
            currencyConversion.setPort(currencyConversion.getPort() +" --> "+port);
        } catch (Exception e) {
            throw new RuntimeException("Exchange not found");
        }
        return currencyConversion;

    }


}
