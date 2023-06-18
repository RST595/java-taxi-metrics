package com.rst.metrics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coordinate {
    public String longitude;
    public String latitude;

    @Override
    public String toString() {
        return longitude + "," + latitude;
    }
}
