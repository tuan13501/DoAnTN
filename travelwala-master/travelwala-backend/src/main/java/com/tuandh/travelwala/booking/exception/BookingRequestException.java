package com.tuandh.travelwala.booking.exception;

import com.tuandh.travelwala.common.exception.WalaException;

public class BookingRequestException extends WalaException {
    public BookingRequestException(String message) {
        this.message = message;
        this.code = 400;
    }
}
