package com.ict.group06.travelwala.accounting.service;

import com.ict.group06.travelwala.model.response.InvoiceResponse;

public interface IUpdateInvoiceStatus {
    InvoiceResponse completeInvoice(String invoiceId);
    InvoiceResponse cancelInvoice(String invoiceId);
}
