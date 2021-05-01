package com.dimitar.currencyconverter.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dimitar.currencyconverter.models.ConversionCurrency;
import com.dimitar.currencyconverter.models.Currency;
import com.dimitar.currencyconverter.services.CurrencyService;

import java.util.List;
import java.util.Optional;

@RestController
public class CurrencyConverterController {

    CurrencyService currencyService;

    public CurrencyConverterController(CurrencyService currencyService) {
       this.currencyService = currencyService;
    }

    //Converts between two currencies
    @RequestMapping(value = "/currency-converter", produces = { "application/json" }, method = RequestMethod.POST)
    public ResponseEntity<Double> convertCurrencies(@RequestBody ConversionCurrency conversionCurrency) {
        Optional<Double> resultOptional = this.currencyService.convert(conversionCurrency);
        if (resultOptional.isPresent()) {
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Lists all currrencies
    @RequestMapping(value = "/currencies", produces = { "application/json" }, method = RequestMethod.GET)
    public ResponseEntity<List<Currency>> getAllCurrencies() {
        return new ResponseEntity<>(this.currencyService.getAllCurrencies(), HttpStatus.OK);
    }
}
