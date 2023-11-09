package com.ict.group06.travelwala.booking.entity;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public abstract class BookingLineItem {
    @Field("unit_price")
    protected double unitPrice;

    @Field("quantity")
    protected int quantity;
}
