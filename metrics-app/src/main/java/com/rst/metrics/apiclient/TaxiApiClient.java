package com.rst.metrics.apiclient;

import com.rst.metrics.dto.Price;
import io.micrometer.core.annotation.Timed;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ownTaxiClient", url = "${app.provider.url}")
public interface TaxiApiClient {
    @Timed("getSinglePrice")
    @GetMapping
    Price getPrice(@RequestParam String clid, @RequestParam String apikey, @RequestParam String rll);
}
