package com.ict.group06.travelwala.booking.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document("bookings")
@Getter
@NoArgsConstructor
public class Booking {
    @Id
    private String id;

    @Field("line_items")
    @Setter
    private List<BookingLineItem> bookingLineItems;

    @Field("contact_id")
    @Setter
    private String contactId;

    @Field("created_at")
    private LocalDateTime createdAt;

    public Booking(List<BookingLineItem> bookingLineItems, String contactId) {
        this.bookingLineItems = bookingLineItems;
        this.contactId = contactId;
        this.createdAt = LocalDateTime.now();
    }
}
