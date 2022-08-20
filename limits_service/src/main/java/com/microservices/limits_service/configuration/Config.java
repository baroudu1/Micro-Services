package com.microservices.limits_service.configuration;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("limits-service")
@Getter @Setter @NoArgsConstructor
public class Config {
    private int minimum;
    private int maximum;
}
