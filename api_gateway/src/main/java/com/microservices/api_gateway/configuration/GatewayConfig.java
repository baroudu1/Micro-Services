package com.microservices.api_gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/currency-exchanges/**")
                        .uri("lb://currency-exchange-service"))
                .route(r -> r.path("/currency-conversion/**")
                        .uri("lb://currency-conversion-service"))
                .build();
    }
}
