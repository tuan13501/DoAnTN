package com.tuandh.travelwala.flight.exception;

import com.tuandh.travelwala.common.exception.WalaException;
import org.springframework.http.HttpStatus;

public class FlightLocationException extends WalaException {
    public FlightLocationException(String message) {
        this.message = message;
        this.code = HttpStatus.BAD_REQUEST.value();
    }
}
