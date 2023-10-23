package com.tuandh.travelwala.mapstruct.mappers;

import com.tuandh.travelwala.accounting.entity.Invoice;
import com.tuandh.travelwala.model.response.InvoiceResponse;
import org.mapstruct.Mapper;

@Mapper
public interface InvoiceMapper {
    InvoiceResponse invoiceToInvoiceResponse(Invoice invoice);
}
