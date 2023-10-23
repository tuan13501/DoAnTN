package com.tuandh.travelwala.accounting.exception;

import com.tuandh.travelwala.common.exception.WalaException;

public class BadPaymentProviderException extends WalaException {
    public BadPaymentProviderException(String message) {
        this.message = message;
        this.code = 503;
    }
}
