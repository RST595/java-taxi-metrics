package com.rst.metrics.scheduler;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class InfoScheduler {
    private final MeterRegistry meterRegistry;

    @Value("${app.client.host}")
    private String host;

    @Scheduled(cron = "${app.schedule.cron}")
    void getAvailableClasses() {
        String logLine = "Call of /info/classes number: %s";
        Counter counter = Counter.builder("info_classes_count").register(meterRegistry);
        Timer timer = meterRegistry.timer("info_classes_duration");
        WebClient webClient = WebClient.builder()
                .baseUrl(host + "/classes")
                .build();

        for (int i = 0; i < 50; i++) {
            callExternalServiceAndRecordMetrics(counter, timer, webClient);
            log.info(String.format(logLine, i + 1));
        }
    }

    private void callExternalServiceAndRecordMetrics(Counter counter, Timer timer, WebClient webClient) {
        long startRequest = System.currentTimeMillis();
        try {
            webClient.get().retrieve().bodyToMono(String.class).block();
        } catch (WebClientRequestException exception) {
            log.error("/info/classes not response");
        }
        timer.record(System.currentTimeMillis() - startRequest, TimeUnit.MILLISECONDS);
        counter.increment();
    }
}
