package com.tuandh.travelwala.flight.exception;

import com.tuandh.travelwala.common.exception.WalaException;
import org.springframework.http.HttpStatus;

public class FlightTimeException extends WalaException {
    public FlightTimeException(String message) {
        this.message = message;
        this.code = HttpStatus.BAD_REQUEST.value();
    }
}
