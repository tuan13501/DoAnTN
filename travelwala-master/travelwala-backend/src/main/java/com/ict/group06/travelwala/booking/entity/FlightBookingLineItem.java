package com.ict.group06.travelwala.booking.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@NoArgsConstructor
@Getter
@Setter
public class FlightBookingLineItem extends BookingLineItem {
    @Field("ticket_id")
    private String ticketId;

    public FlightBookingLineItem(String ticketId, double unitPrice) {
        this.ticketId = ticketId;
        this.unitPrice = unitPrice;
        this.quantity = 1;
    }
}
