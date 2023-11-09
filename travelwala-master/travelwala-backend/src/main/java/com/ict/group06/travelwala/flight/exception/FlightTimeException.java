package com.ict.group06.travelwala.flight.exception;

import com.ict.group06.travelwala.common.exception.WalaException;
import org.springframework.http.HttpStatus;

public class FlightTimeException extends WalaException {
    public FlightTimeException(String message) {
        this.message = message;
        this.code = HttpStatus.BAD_REQUEST.value();
    }
}
