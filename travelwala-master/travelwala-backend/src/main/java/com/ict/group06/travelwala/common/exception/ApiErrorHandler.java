package com.ict.group06.travelwala.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiErrorHandler {
    @ExceptionHandler(WalaException.class)
    public ResponseEntity<WalaErrorResponse> handleApiException(WalaException exception) {
        return ResponseEntity.status(exception.getCode()).body(exception.getErrorResponse());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<WalaErrorResponse> handleValidationException(BindingResult bindingResult) {
        WalaErrorResponse errorResponse = new WalaErrorResponse(
                bindingResult.getClass().getName(),
                bindingResult.getAllErrors().get(0).getDefaultMessage(),
                400);
        return ResponseEntity.status(errorResponse.getCode()).body(errorResponse);
    }
}
