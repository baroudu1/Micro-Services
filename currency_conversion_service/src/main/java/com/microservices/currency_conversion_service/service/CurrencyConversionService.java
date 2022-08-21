package com.microservices.currency_conversion_service.service;

import com.microservices.currency_conversion_service.helper.CurrencyConversionProxy;
import com.microservices.currency_conversion_service.model.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

        CurrencyConversion currencyConversion = currencyConversionProxy.getCurrencyConversion(from, to);

        assert currencyConversion != null;
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(quantity * currencyConversion.getConversionMultiple());
        currencyConversion.setPort(currencyConversion.getPort() +" --> "+port);
        return currencyConversion;
    }


}
