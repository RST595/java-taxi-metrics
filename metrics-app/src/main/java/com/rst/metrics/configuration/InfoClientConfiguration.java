package com.rst.metrics.configuration;

import com.rst.metrics.apiclient.InfoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * Make bean of InfoClient with base URL
 * Now, all methods from base url, could simply define in InfoClient interface
 */
@Configuration
public class InfoClientConfiguration {
    @Value("${app.client.host}")
    private String host;

    @Bean
    InfoClient infoClient() {
        WebClient client = WebClient.builder()
                .baseUrl(host)
                .build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(client))
                .build();

        return factory.createClient(InfoClient.class);
    }
}
