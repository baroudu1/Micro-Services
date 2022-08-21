package com.microservices.currency_exchange_service.repository;

import com.microservices.currency_exchange_service.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange, Long> {

    @Override
    List<CurrencyExchange> findAll();
    CurrencyExchange findByFromAndTo(String from, String to);
    boolean existsByFromAndTo(String from, String to);
}
