package com.ict.group06.travelwala.accounting.repository;

import com.ict.group06.travelwala.accounting.entity.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, String> {
}
