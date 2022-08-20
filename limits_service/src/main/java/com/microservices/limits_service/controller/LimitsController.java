package com.microservices.limits_service.controller;


import com.microservices.limits_service.configuration.Config;
import com.microservices.limits_service.model.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/limits")
public class LimitsController {
    @Autowired
    private Config config;

    @GetMapping
    public Limits getLimits() {
        return new Limits(config.getMinimum(), config.getMaximum());
    }
}
