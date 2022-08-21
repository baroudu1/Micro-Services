package com.microservices.currency_exchange_service.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "currency_exchange")
@Getter @Setter @NoArgsConstructor @ToString
public class CurrencyExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotEmpty
    @Column(name = "currency_from")
    private String from;

    @NotEmpty
    @Column(name = "currency_to")
    private String to;

    @JsonProperty("conversion_multiple")
    private Double conversionMultiple;

    private String port;

    public CurrencyExchange(String from, String to, Double conversionMultiple ,String port) {
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
        this.port = port;
    }

}
