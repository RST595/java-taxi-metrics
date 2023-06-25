package com.rst.taxiprovider.controller;

import com.rst.metrics.dto.Coordinate;
import com.rst.taxiprovider.calculator.DistanceCalculator;
import com.rst.taxiprovider.model.TaxiClassEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {
    private final DistanceCalculator distanceCalculator;

    @GetMapping("/classes")
    public List<String> getClasses() {
        return Arrays.stream(TaxiClassEnum.values()).map(TaxiClassEnum::toString).collect(Collectors.toList());
    }

    @PostMapping("/distance")
    public String getLength(@RequestBody List<Coordinate> coordinates) {
        return distanceCalculator.calculate(coordinates);
    }
}
