package com.ict.group06.travelwala.accounting.service.impl;

import com.ict.group06.travelwala.accounting.entity.Invoice;
import com.ict.group06.travelwala.accounting.enumeration.InvoiceStatus;
import com.ict.group06.travelwala.accounting.repository.InvoiceRepository;
import com.ict.group06.travelwala.accounting.service.ICreateInvoice;
import com.ict.group06.travelwala.accounting.service.IUpdateInvoiceStatus;
import com.ict.group06.travelwala.common.exception.RecordNotFoundException;
import com.ict.group06.travelwala.mapstruct.mappers.InvoiceMapper;
import com.ict.group06.travelwala.model.response.InvoiceResponse;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements ICreateInvoice, IUpdateInvoiceStatus {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper mapper = Mappers.getMapper(InvoiceMapper.class);

    @Override
    public InvoiceResponse createInvoice(String bookingId, double totalAmount) {
        Invoice invoice = invoiceRepository.save(new Invoice(totalAmount, bookingId));

        return mapper.invoiceToInvoiceResponse(invoice);
    }

    @Override
    public InvoiceResponse completeInvoice(String invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(
                () -> new RecordNotFoundException("No invoice found for id " + invoiceId)
        );

        invoice.setStatus(InvoiceStatus.PAID);
        invoice.setPaidAt(LocalDateTime.now());

        return mapper.invoiceToInvoiceResponse(invoiceRepository.save(invoice));
    }

    @Override
    public InvoiceResponse cancelInvoice(String invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(
                () -> new RecordNotFoundException("No invoice found for id " + invoiceId)
        );

        invoice.setStatus(InvoiceStatus.CANCELLED);

        return mapper.invoiceToInvoiceResponse(invoiceRepository.save(invoice));
    }
}
