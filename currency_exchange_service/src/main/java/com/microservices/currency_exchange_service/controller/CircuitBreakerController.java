package com.microservices.currency_exchange_service.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/sample-api")
public class CircuitBreakerController {
    @GetMapping
//    @Retry(name = "default", fallbackMethod = "fallbackSampleApi")
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackSampleApi")
    public String sampleApi() {
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8044/sample-api", String.class);
        return forEntity.getBody();
    }
    public String fallbackSampleApi(Exception e) {
        return "Fallback Sample Api";
    }
}
