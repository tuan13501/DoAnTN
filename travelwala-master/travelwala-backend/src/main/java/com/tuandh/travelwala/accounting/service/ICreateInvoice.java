package com.tuandh.travelwala.accounting.service;

import com.tuandh.travelwala.model.response.InvoiceResponse;

public interface ICreateInvoice {
    InvoiceResponse createInvoice(String bookingId, double totalAmount);
}
