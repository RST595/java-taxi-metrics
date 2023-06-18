package com.rst.metrics.controller;

import com.rst.metrics.apiclient.InfoClient;
import com.rst.metrics.dto.Coordinate;
import com.rst.metrics.entity.MomentPrice;
import com.rst.metrics.service.TaxiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {
    private final InfoClient infoClient;

    @GetMapping
    public List<String> getClasses() {
        return infoClient.getClasses();
    }

    @PostMapping
    public String getLength(@RequestBody List<Coordinate> coordinates) {
        return infoClient.getLength(coordinates);
    }
}
