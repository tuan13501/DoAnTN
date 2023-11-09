package com.ict.group06.travelwala.mapstruct.mappers;

import com.ict.group06.travelwala.accounting.entity.Invoice;
import com.ict.group06.travelwala.model.response.InvoiceResponse;
import org.mapstruct.Mapper;

@Mapper
public interface InvoiceMapper {
    InvoiceResponse invoiceToInvoiceResponse(Invoice invoice);
}
