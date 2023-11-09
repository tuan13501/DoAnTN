package com.ict.group06.travelwala.ticket.service;

import com.ict.group06.travelwala.model.request.CreateBookingFlightSpecs;
import com.ict.group06.travelwala.model.response.CreateTicketResponse;

import java.util.List;

public interface ICreateTicket {
    List<CreateTicketResponse> createTickets(CreateBookingFlightSpecs.TravellerSpecs passengers, String flightId, String seatClass);
}
