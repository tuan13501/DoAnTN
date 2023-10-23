package com.tuandh.travelwala.flight.service;

public interface IAvailableSeatsCheck {
    boolean isEnoughSeats(String flightId, String seatClass, int numberOfSeats);
}
