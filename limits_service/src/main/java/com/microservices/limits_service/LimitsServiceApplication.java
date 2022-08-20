package com.microservices.limits_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class LimitsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LimitsServiceApplication.class, args);
    }

}
