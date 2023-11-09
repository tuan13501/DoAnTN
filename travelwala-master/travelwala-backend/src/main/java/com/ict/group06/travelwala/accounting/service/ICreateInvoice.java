package com.ict.group06.travelwala.accounting.service;

import com.ict.group06.travelwala.model.response.InvoiceResponse;

public interface ICreateInvoice {
    InvoiceResponse createInvoice(String bookingId, double totalAmount);
}
