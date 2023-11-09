package com.ict.group06.travelwala.common.enumeration.seatclass;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum SeatClassEnum {
    ECONOMY("Economy"), BUSINESS("Business");

    private String value;

    SeatClassEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @JsonCreator
    public static SeatClassEnum getEnumFromValue(String value) {
        return Stream.of(SeatClassEnum.values())
                .filter(s -> s.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
