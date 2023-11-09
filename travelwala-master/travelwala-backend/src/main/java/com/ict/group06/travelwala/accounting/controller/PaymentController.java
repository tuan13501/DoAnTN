package com.ict.group06.travelwala.accounting.controller;

import com.ict.group06.travelwala.accounting.model.CreatePaymentRequest;
import com.ict.group06.travelwala.accounting.service.ICreatePayment;
import com.ict.group06.travelwala.accounting.service.IUpdateInvoiceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
@CrossOrigin("*")
public class PaymentController {
    private final ICreatePayment createPaymentService;
    private final IUpdateInvoiceStatus iUpdateInvoiceStatus;

    @PostMapping("/stripe/create-payment-intent")
    ResponseEntity<?> createPaymentIntent(@RequestBody CreatePaymentRequest createPaymentRequest) {
        return ResponseEntity.ok().body(createPaymentService.pay(createPaymentRequest));
    }

    @PutMapping("/invoices/{invoiceId}/complete-invoice")
    ResponseEntity<?> updateInvoiceStatus(@PathVariable String invoiceId) {
        return ResponseEntity.ok().body(iUpdateInvoiceStatus.completeInvoice(invoiceId));
    }
}
