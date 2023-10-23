package com.tuandh.travelwala.ticket.service;

import com.tuandh.travelwala.model.request.CreateBookingFlightSpecs;
import com.tuandh.travelwala.model.response.CreateTicketResponse;

import java.util.List;

public interface ICreateTicket {
    List<CreateTicketResponse> createTickets(CreateBookingFlightSpecs.TravellerSpecs passengers, String flightId, String seatClass);
}
