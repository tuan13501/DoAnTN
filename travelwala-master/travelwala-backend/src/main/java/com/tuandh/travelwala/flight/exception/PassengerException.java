package com.tuandh.travelwala.flight.exception;

import com.tuandh.travelwala.common.exception.WalaException;
import org.springframework.http.HttpStatus;

public class PassengerException extends WalaException {
    public PassengerException(String message) {
        this.message = message;
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}
