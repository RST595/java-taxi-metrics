package com.rst.metrics.apiclient;

import com.rst.metrics.dto.Coordinate;
import io.micrometer.observation.annotation.Observed;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

/**
 * Together with InfoClient bean in InfoClientConfiguration, tune WebClient
 * Now we could define new methods which we are calling from remote server by http request
 * By adding in this interface
 */
public interface InfoClient {

    @GetExchange("/classes")
    @Observed(name = "info.get.classes")
    List<String> getClasses();

    @PostExchange("/distance")
    String getLength(@RequestBody List<Coordinate> coordinates);
}
