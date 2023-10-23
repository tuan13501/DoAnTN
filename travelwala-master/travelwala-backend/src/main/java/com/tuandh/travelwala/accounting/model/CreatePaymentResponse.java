package com.tuandh.travelwala.accounting.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentResponse {
    private String clientSecret;
    private String currency;
    private Long amount;
}
