package com.ict.group06.travelwala.common.exception;

import org.springframework.http.HttpStatus;

public class RecordNotFoundException extends WalaException {
    public RecordNotFoundException(String message) {
        this.message = message;
        this.code = HttpStatus.NOT_FOUND.value();
    }
}
