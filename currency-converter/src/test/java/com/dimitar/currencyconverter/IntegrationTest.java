package com.dimitar.currencyconverter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.dimitar.currencyconverter.models.ConversionCurrency;
import com.dimitar.currencyconverter.models.Currency;
import com.dimitar.currencyconverter.repositories.CurrencyRepository;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource("classpath:application.properties")
public class IntegrationTest {


    @Mock
    private CurrencyRepository repository;

    private RestTemplate restTemplate;

    private String basePath = "http://localhost:8080";

    @Before
    public void setup() {
        restTemplate = new RestTemplate();
    }


    @Test
    public void convertShouldBeSuccessful() {
        Currency currencyEUR = new Currency("EUR", 1);
        Currency currencyUSD = new Currency("USD", 1.15795);

        Mockito.when(repository.findById("EUR")).thenReturn(Optional.of(currencyEUR));
        Mockito.when(repository.findById("USD")).thenReturn(Optional.of(currencyUSD));

        ConversionCurrency conversionCurrency = new ConversionCurrency("USD", "EUR", 10);

        ResponseEntity<Double> responseEntity = restTemplate.postForEntity(basePath + "/currency-converter",
                conversionCurrency, Double.class);

        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

    }

}
