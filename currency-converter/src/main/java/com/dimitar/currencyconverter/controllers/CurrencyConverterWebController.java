package com.dimitar.currencyconverter.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CurrencyConverterWebController {

    @GetMapping("/")
    public String serveWebPage() {
        return "index";
    }
}
