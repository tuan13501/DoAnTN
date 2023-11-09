package com.ict.group06.travelwala.flight.exception;

import com.ict.group06.travelwala.common.exception.WalaException;
import org.springframework.http.HttpStatus;

public class PassengerException extends WalaException {
    public PassengerException(String message) {
        this.message = message;
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}
