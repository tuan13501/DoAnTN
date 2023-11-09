package com.ict.group06.travelwala.flight.service;

import com.ict.group06.travelwala.common.enumeration.seatclass.SeatClassEnum;
import com.ict.group06.travelwala.ticket.enumeration.TicketEnum;

public interface ICalculateFlightFare {
    Double getFlightFare(TicketEnum ticketType, SeatClassEnum seatClass, String flightId);
}
