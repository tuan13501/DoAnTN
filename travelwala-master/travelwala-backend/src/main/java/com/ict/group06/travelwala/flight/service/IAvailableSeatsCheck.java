package com.ict.group06.travelwala.flight.service;

public interface IAvailableSeatsCheck {
    boolean isEnoughSeats(String flightId, String seatClass, int numberOfSeats);
}
