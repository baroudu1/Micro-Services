package com.microservices.currency_exchange_service;

import com.microservices.currency_exchange_service.model.CurrencyExchange;
import com.microservices.currency_exchange_service.repository.CurrencyExchangeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;

import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class CurrencyExchangeServiceApplication implements CommandLineRunner {

    @Autowired
    private CurrencyExchangeRepo currencyExchangeRepo;
    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(CurrencyExchangeServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String port = environment.getProperty("local.server.port");
        currencyExchangeRepo.saveAll(List.of(
                new CurrencyExchange("USD", "INR", 67.0, port),
                new CurrencyExchange("DH", "USD", 0.1 , port)
        ));
    }
}
