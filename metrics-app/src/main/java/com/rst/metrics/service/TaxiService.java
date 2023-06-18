package com.rst.metrics.service;

import com.rst.metrics.apiclient.TaxiApiClient;
import com.rst.metrics.dto.Coordinate;
import com.rst.metrics.dto.Price;
import com.rst.metrics.entity.MomentPrice;
import com.rst.metrics.properties.TaxiProviderProperties;
import com.rst.metrics.repository.PriceRepository;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TaxiService {
    private final TaxiProviderProperties taxiProviderProperties;
    private final TaxiApiClient taxiApiClient;
    private final PriceRepository priceRepository;
    private final AtomicInteger price = new AtomicInteger();

    public TaxiService(TaxiProviderProperties taxiProviderProperties,
                       TaxiApiClient taxiApiClient,
                       PriceRepository priceRepository,
                       MeterRegistry meterRegistry) {
        this.taxiProviderProperties = taxiProviderProperties;
        this.taxiApiClient = taxiApiClient;
        this.priceRepository = priceRepository;
        meterRegistry.gauge("taxiPrice", price); // Add metric to prometheus. Now price change recording to it
    }

    // Tune this annotation in PerformanceTrackerHandler and ObservationAspectConfiguration
    @Observed(name = "taxi.get.price")
    public void getPrice(Coordinate startPoint, Coordinate endPoint) {
        String rll = startPoint.toString() + "~" + endPoint.toString();
        String clid = taxiProviderProperties.getClid();
        String apiKey = taxiProviderProperties.getApiKey();

        Price currentPrice = taxiApiClient.getPrice(clid, apiKey, rll);
        if (Optional.ofNullable(currentPrice).map(Price::getOptions).isEmpty()) {
            throw new RuntimeException("Options is empty");
        }

        double priceDouble = currentPrice.options.get(0).getPrice();

        price.set((int) priceDouble);

        MomentPrice momentPrice = new MomentPrice(LocalDateTime.now(ZoneId.of("Europe/Paris")), priceDouble);
        priceRepository.save(momentPrice);
    }

    @Timed("getAllTaxiPrices") // Add metric to prometheus
    public List<MomentPrice> getAllPrice() {
        return priceRepository.findAll();
    }
}
