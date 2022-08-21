package com.microservices.currency_exchange_service.controller;

import com.microservices.currency_exchange_service.ExceptionHandler.ExchangeAlreadyExists;
import com.microservices.currency_exchange_service.ExceptionHandler.ExchangeNotFound;
import com.microservices.currency_exchange_service.model.CurrencyExchange;
import com.microservices.currency_exchange_service.repository.CurrencyExchangeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/currency-exchanges")
public class CurrencyExchangeController {
    @Autowired
    private CurrencyExchangeRepo currencyExchangeRepo;

    @Autowired
    private Environment environment;

    @GetMapping
    public List<CurrencyExchange> getAllExchanges() {
        return currencyExchangeRepo.findAll();
    }
    @GetMapping("{idExchange}")
    public CurrencyExchange getExchange(@PathVariable Long idExchange) {
        return currencyExchangeRepo.findById(idExchange).orElseThrow(() -> new ExchangeNotFound("Exchange not found"));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CurrencyExchange saveExchange(@Valid @RequestBody CurrencyExchange currencyExchange) {
        if (currencyExchangeRepo.existsByFromAndTo(currencyExchange.getFrom(), currencyExchange.getTo())
                || currencyExchangeRepo.existsByFromAndTo(currencyExchange.getTo(), currencyExchange.getFrom())) {
            throw new ExchangeAlreadyExists("Exchange already exists");
        }
//        System.err.println(currencyExchange.toString());
        String port = environment.getProperty("local.server.port");
        currencyExchange.setPort(port);
        return currencyExchangeRepo.save(currencyExchange);
    }

    @PutMapping("{idExchange}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CurrencyExchange updateExchange(@PathVariable Long idExchange, @Valid @RequestBody CurrencyExchange currencyExchange) {
        CurrencyExchange currencyExchange1 = currencyExchangeRepo.findById(idExchange)
                .orElseThrow(() -> new ExchangeNotFound("Exchange not found"));
        if (currencyExchangeRepo.existsByFromAndTo(currencyExchange.getFrom(), currencyExchange.getTo())
                || currencyExchangeRepo.existsByFromAndTo(currencyExchange.getTo(), currencyExchange.getFrom())) {
            throw new ExchangeAlreadyExists("Exchange already exists");
        }

        currencyExchange1.setFrom(currencyExchange.getFrom());
        currencyExchange1.setTo(currencyExchange.getTo());
        currencyExchange1.setConversionMultiple(currencyExchange.getConversionMultiple());

        String port = environment.getProperty("local.server.port");
        currencyExchange1.setPort(port);

        return currencyExchangeRepo.save(currencyExchange1);
    }

    @DeleteMapping("{idExchange}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExchange(@PathVariable Long idExchange) {
        if (!currencyExchangeRepo.existsById(idExchange)) {
            throw new ExchangeNotFound("Exchange not found");
        }
        currencyExchangeRepo.deleteById(idExchange);
    }


    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange getCurrencyExchange(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchangeFromTo = currencyExchangeRepo.findByFromAndTo(from, to);
        if (currencyExchangeFromTo != null) {
            return currencyExchangeFromTo;
        }
        CurrencyExchange currencyExchangeToFrom = currencyExchangeRepo.findByFromAndTo(to, from);
        if (currencyExchangeToFrom == null) {
            throw new ExchangeNotFound("Exchange not found");
        }
        currencyExchangeToFrom.setFrom(from);
        currencyExchangeToFrom.setTo(to);
        currencyExchangeToFrom.setConversionMultiple(1 / currencyExchangeToFrom.getConversionMultiple());
        return currencyExchangeToFrom;
    }


}
