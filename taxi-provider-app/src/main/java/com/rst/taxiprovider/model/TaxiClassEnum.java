package com.rst.taxiprovider.model;

import lombok.Getter;

@Getter
public enum TaxiClassEnum {
    ECONOMY(1.0),
    COMFORT(1.1),
    COMFORT_PLUS(1.2),
    BUSINESS(1.5);

    TaxiClassEnum(Double multi) {
        this.multi = multi;
    }

    private final double multi;
}
