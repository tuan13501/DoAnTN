package com.ict.group06.travelwala.accounting.entity;

import com.ict.group06.travelwala.accounting.enumeration.InvoiceStatus;
import com.ict.group06.travelwala.booking.entity.Booking;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document("invoices")
@Getter
@NoArgsConstructor
public class Invoice {
    @Id
    private String id;

    @Field("booking_id")
    @Setter
    private String bookingId;

    @Field("total_cost")
    @Setter
    private double totalCost;

    @Field("status")
    @Setter
    private InvoiceStatus status;

    @Field("created_at")
    @Setter
    private LocalDateTime createdAt;

    @Field("paid_at")
    @Setter
    private LocalDateTime paidAt;

    public Invoice(double totalCost, String bookingId) {
        this.totalCost = totalCost;
        this.bookingId = bookingId;
        this.status = InvoiceStatus.UNPAID;
        this.createdAt = LocalDateTime.now();
    }
}
