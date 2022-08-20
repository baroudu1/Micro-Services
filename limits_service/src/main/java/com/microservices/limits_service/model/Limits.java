package com.microservices.limits_service.model;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Limits {
    private int minimum;
    private int maximum;
}
