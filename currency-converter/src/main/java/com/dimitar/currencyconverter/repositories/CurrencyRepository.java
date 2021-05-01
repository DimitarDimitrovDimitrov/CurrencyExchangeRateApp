package com.dimitar.currencyconverter.repositories;


import org.springframework.data.repository.CrudRepository;

import com.dimitar.currencyconverter.models.Currency;

import java.util.List;

public interface CurrencyRepository extends CrudRepository<Currency, String> {

    @Override
    List<Currency> findAll();
}

