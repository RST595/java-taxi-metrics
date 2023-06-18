package com.rst.taxiprovider.calculator;

import com.rst.metrics.dto.Coordinate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DistanceCalculator {
    public static final String INVALID_INPUT_MESSAGE = "Invalid input coordinate format: %s";

    public double calculate(MutablePair<Coordinate, Coordinate> coordinates) {
        String stringCoordinate = coordinates.left.toString() + ", " + coordinates.right.toString();
        String errorMessage = String.format(INVALID_INPUT_MESSAGE, stringCoordinate);

        try {
            double startLatitude = Double.parseDouble(coordinates.left.getLatitude());
            double startLongitude = Double.parseDouble(coordinates.left.getLongitude());
            double finishLatitude = Double.parseDouble(coordinates.right.getLatitude());
            double finishLongitude = Double.parseDouble(coordinates.right.getLongitude());

            double sum = Math.pow(startLatitude - finishLatitude, 2) + Math.pow(startLongitude - finishLongitude, 2);
            return Math.pow(sum, 0.5);

        } catch (NumberFormatException e) {
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    public String calculate(List<Coordinate> coordinates) {
        if (coordinates.size() != 2) {
            String errorMessage = "Input array for calculate distance request, should be equal 2";
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        return String.format("%.2f", calculate(new MutablePair<>(coordinates.get(0), coordinates.get(1))));
    }
}
