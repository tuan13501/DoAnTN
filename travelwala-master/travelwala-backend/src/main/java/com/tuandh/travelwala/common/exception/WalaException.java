package com.tuandh.travelwala.common.exception;

import lombok.Getter;

@Getter
public abstract class WalaException extends RuntimeException {
    protected String message;
    protected int code;

    public WalaErrorResponse getErrorResponse() {
        return new WalaErrorResponse(this.getClass().getName(), this.message, this.code);
    }
}
