package com.rst.metrics.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.provider")
public class TaxiProviderProperties {
    private String clid;
    private String apiKey;
}
