package com.company.currencyTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/currency")
public class Controller {

    private Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping
    @RequestMapping("/fx_rates")
    public Result getCurrencyInfo(@RequestParam String currency,
                                  @RequestParam String dateFrom,
                                  @RequestParam String dateTo) {
        return service.getFinalResult(currency, dateFrom, dateTo);
    }

}
