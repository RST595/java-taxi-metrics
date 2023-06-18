package com.rst.metrics.controller;

import com.rst.metrics.entity.MomentPrice;
import com.rst.metrics.service.TaxiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class PriceController {
    private final TaxiService taxiService;

    @GetMapping
    public List<MomentPrice> getAllPrice() {
        return taxiService.getAllPrice();
    }
}
