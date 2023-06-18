package com.rst.metrics.aspect;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * Tune @Observed(name = "...")
 * Also need to configure bean ObservedAspect and register this handler there
 * Now each method with this annotation shown in metrics: <a href="http://localhost:8080/actuator/metrics">...</a>
 * To get metrics: GET /actuator/metrics/metricName
 */
@Slf4j
public class PerformanceTrackerHandler implements ObservationHandler<Observation.Context> {
    @Override
    public void onStart(Observation.Context context) {
        log.info("Execution of method: {}, started.", context.getName());
        context.put("time", System.currentTimeMillis());
    }

    @Override
    public void onError(Observation.Context context) {
        log.info("Error occurred: {}", Optional.ofNullable(context.getError())
                                            .map(Throwable::getMessage)
                                            .orElse(""));
    }

    @Override
    public void onEvent(Observation.Event event, Observation.Context context) {
        ObservationHandler.super.onEvent(event, context);
    }

    @Override
    public void onScopeOpened(Observation.Context context) {
        ObservationHandler.super.onScopeOpened(context);
    }

    @Override
    public void onScopeClosed(Observation.Context context) {
        ObservationHandler.super.onScopeClosed(context);
    }

    @Override
    public void onScopeReset(Observation.Context context) {
        ObservationHandler.super.onScopeReset(context);
    }

    @Override
    public void onStop(Observation.Context context) {
        long timeDelta = System.currentTimeMillis() - context.getOrDefault("time", 0L);
        log.info("Execution of method: {} stopped. Duration: {}", context.getName(), timeDelta);
    }

    @Override
    public boolean supportsContext(Observation.Context context) {
        return true;
    }
}
