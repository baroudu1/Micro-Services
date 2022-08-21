package com.microservices.currency_conversion_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class CurrencyConversion {

    private String from;

    private String to;

    @JsonProperty("conversion_multiple")
    private Double conversionMultiple;

    private int quantity;

    @JsonProperty("total_calculated_amount")
    private Double totalCalculatedAmount;

    private String port;

}
