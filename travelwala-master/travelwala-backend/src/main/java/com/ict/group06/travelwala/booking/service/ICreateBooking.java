package com.ict.group06.travelwala.booking.service;

import com.ict.group06.travelwala.booking.model.response.CreateBookingResponse;
import com.ict.group06.travelwala.model.request.CreateBookingRequest;

public interface ICreateBooking {
    CreateBookingResponse createBooking(CreateBookingRequest bookingRequest);
}
