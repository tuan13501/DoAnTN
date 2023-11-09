package com.ict.group06.travelwala.flight.service;

import com.ict.group06.travelwala.common.enumeration.seatclass.SeatClassEnum;

public interface IOccupySeats {
    void occupy(String flightId, int numberOfSeats, SeatClassEnum seatClass);
}
