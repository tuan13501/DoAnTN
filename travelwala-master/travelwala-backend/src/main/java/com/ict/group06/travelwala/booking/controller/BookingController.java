package com.ict.group06.travelwala.booking.controller;

import com.ict.group06.travelwala.booking.service.ICreateBooking;
import com.ict.group06.travelwala.model.request.CreateBookingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/bookings")
public class BookingController {
    private final ICreateBooking createBooking;

    @PostMapping()
    public ResponseEntity<?> book(@Valid @RequestBody CreateBookingRequest bookingRequest) {
        return ResponseEntity.ok().body(createBooking.createBooking(bookingRequest));
    }
}
