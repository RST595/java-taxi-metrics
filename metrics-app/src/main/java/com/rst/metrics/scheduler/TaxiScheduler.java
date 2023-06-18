package com.rst.metrics.scheduler;

import com.rst.metrics.dto.Coordinate;
import com.rst.metrics.properties.CoordinateProperties;
import com.rst.metrics.service.TaxiService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaxiScheduler {
    private final CoordinateProperties coordinateProperties;
    private final TaxiService taxiService;

    @Timed("updateTaxiPrices") // Add metric to prometheus
    @Scheduled(cron = "${app.schedule.cron}")
    public void updatePrice() {
        Coordinate startPoint = new Coordinate(coordinateProperties.getStartLongitude(),
                coordinateProperties.getStartLatitude());
        Coordinate endPoint = new Coordinate(coordinateProperties.getFinishLongitude(),
                coordinateProperties.getFinishLatitude());

        taxiService.getPrice(startPoint, endPoint);
    }
}
