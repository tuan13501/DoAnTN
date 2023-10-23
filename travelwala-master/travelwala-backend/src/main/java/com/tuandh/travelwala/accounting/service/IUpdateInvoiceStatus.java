package com.tuandh.travelwala.accounting.service;

import com.tuandh.travelwala.model.response.InvoiceResponse;

public interface IUpdateInvoiceStatus {
    InvoiceResponse completeInvoice(String invoiceId);
    InvoiceResponse cancelInvoice(String invoiceId);
}
