package com.rst.taxiprovider.controller;

import com.rst.metrics.dto.Price;
import com.rst.taxiprovider.service.TaxiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taxi")
@RequiredArgsConstructor
public class TaxiController {
    private final TaxiService taxiService;

    @GetMapping("/price")
    public Price getPrice(@RequestParam String clid, @RequestParam String apikey, @RequestParam String rll) {
        return taxiService.getPrice(clid, apikey, rll);
    }
}
