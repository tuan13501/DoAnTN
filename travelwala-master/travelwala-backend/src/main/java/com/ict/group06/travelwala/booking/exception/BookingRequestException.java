package com.ict.group06.travelwala.booking.exception;

import com.ict.group06.travelwala.common.exception.WalaException;

public class BookingRequestException extends WalaException {
    public BookingRequestException(String message) {
        this.message = message;
        this.code = 400;
    }
}
