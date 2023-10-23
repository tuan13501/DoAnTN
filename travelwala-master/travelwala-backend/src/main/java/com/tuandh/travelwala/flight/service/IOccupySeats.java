package com.tuandh.travelwala.flight.service;

import com.tuandh.travelwala.common.enumeration.seatclass.SeatClassEnum;

public interface IOccupySeats {
    void occupy(String flightId, int numberOfSeats, SeatClassEnum seatClass);
}
