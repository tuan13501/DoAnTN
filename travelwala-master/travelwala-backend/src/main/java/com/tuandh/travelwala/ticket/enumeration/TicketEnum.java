package com.tuandh.travelwala.ticket.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum TicketEnum {
    ADULT("Adult"), CHILD("Child"), INFANT("Infant");

    private String value;

    TicketEnum(String value) {
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
    public static TicketEnum getEnumFromValue(String value) {
        return Stream.of(TicketEnum.values())
                .filter(s -> s.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
