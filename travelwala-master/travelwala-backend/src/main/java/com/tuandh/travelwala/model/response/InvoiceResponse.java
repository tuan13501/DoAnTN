package com.tuandh.travelwala.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class InvoiceResponse {
    private String id;
    private String bookingId;
    private Double totalAmount;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime paidAt;
}
