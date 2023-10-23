package com.tuandh.travelwala.flight.service;

import com.tuandh.travelwala.common.enumeration.seatclass.SeatClassEnum;
import com.tuandh.travelwala.ticket.enumeration.TicketEnum;

public interface ICalculateFlightFare {
    Double getFlightFare(TicketEnum ticketType, SeatClassEnum seatClass, String flightId);
}
