package com.ict.group06.travelwala.accounting.exception;

import com.ict.group06.travelwala.common.exception.WalaException;

public class BadPaymentProviderException extends WalaException {
    public BadPaymentProviderException(String message) {
        this.message = message;
        this.code = 503;
    }
}
