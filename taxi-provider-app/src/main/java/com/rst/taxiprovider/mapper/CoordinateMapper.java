package com.rst.taxiprovider.mapper;

import com.rst.metrics.dto.Coordinate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CoordinateMapper {
    public static final String INVALID_INPUT_MESSAGE = "Invalid input rll data: %s";

    public MutablePair<Coordinate, Coordinate> parseRll(String rll) {
        String errorMessage = String.format(INVALID_INPUT_MESSAGE, rll);

        String[] startAndFinish = rll.split("~");
        if (startAndFinish.length != 2) {
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        String[] startCoordinate = startAndFinish[0].split(",");
        String[] finishCoordinate = startAndFinish[1].split(",");
        if (startCoordinate.length != 2 || finishCoordinate.length != 2) {
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        Coordinate start = new Coordinate(startCoordinate[0], startCoordinate[1]);
        Coordinate finish = new Coordinate(finishCoordinate[0], finishCoordinate[1]);

        return new MutablePair<>(start, finish);
    }
}
