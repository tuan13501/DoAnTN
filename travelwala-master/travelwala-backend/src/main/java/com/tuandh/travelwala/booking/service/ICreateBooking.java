package com.tuandh.travelwala.booking.service;

import com.tuandh.travelwala.booking.model.response.CreateBookingResponse;
import com.tuandh.travelwala.model.request.CreateBookingRequest;

public interface ICreateBooking {
    CreateBookingResponse createBooking(CreateBookingRequest bookingRequest);
}
