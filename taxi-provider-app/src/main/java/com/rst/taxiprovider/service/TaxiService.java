package com.rst.taxiprovider.service;

import com.rst.metrics.dto.Option;
import com.rst.metrics.dto.Price;
import com.rst.taxiprovider.calculator.DistanceCalculator;
import com.rst.taxiprovider.mapper.CoordinateMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.rst.taxiprovider.model.TaxiClassEnum.ECONOMY;

@Service
@RequiredArgsConstructor
public class TaxiService {
    private final CoordinateMapper coordinateMapper;
    private final DistanceCalculator distanceCalculator;

    public Price getPrice(String clid, String apikey, String rll) {
        if (StringUtils.isBlank(clid) || StringUtils.isBlank(apikey)) {
            return null;
        }

        double distance = distanceCalculator.calculate(coordinateMapper.parseRll(rll));

        Price result = new Price();
        result.setDistance(distance);
        result.setCurrency("RUB");
        result.setOptions(initDefaultOption(distance));

        return result;
    }

    private List<Option> initDefaultOption(double distance) {
        Option option = new Option();
        option.setClass_name(ECONOMY.toString());
        option.setPrice(distance * ECONOMY.getMulti() * 10_000 * Math.random());
        option.setWaiting_time(Math.random() * 20);
        return List.of(option);
    }
}
