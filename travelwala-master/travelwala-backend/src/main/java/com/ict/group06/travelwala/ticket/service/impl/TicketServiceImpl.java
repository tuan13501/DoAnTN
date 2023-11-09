package com.ict.group06.travelwala.ticket.service.impl;

import com.ict.group06.travelwala.common.enumeration.seatclass.SeatClassEnum;
import com.ict.group06.travelwala.flight.service.ICalculateFlightFare;
import com.ict.group06.travelwala.flight.service.IOccupySeats;
import com.ict.group06.travelwala.model.request.PassengerRequest;
import com.ict.group06.travelwala.ticket.enumeration.TicketEnum;
import com.ict.group06.travelwala.flight.service.IAvailableSeatsCheck;
import com.ict.group06.travelwala.model.request.CreateBookingFlightSpecs;
import com.ict.group06.travelwala.model.response.CreateTicketResponse;
import com.ict.group06.travelwala.model.response.PassengerResponse;
import com.ict.group06.travelwala.passenger.service.PassengerService;
import com.ict.group06.travelwala.ticket.entity.Ticket;
import com.ict.group06.travelwala.ticket.repository.TicketRepository;
import com.ict.group06.travelwala.ticket.service.ICreateTicket;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements ICreateTicket {
    private TicketRepository ticketRepository;
    private IAvailableSeatsCheck seatsCheck;
    private PassengerService passengerService;
    private ICalculateFlightFare calculateFlightFare;
    private IOccupySeats occupySeats;

    @Override
    public List<CreateTicketResponse> createTickets(CreateBookingFlightSpecs.TravellerSpecs passengers, String flightId, String seatClass) {
        // check if there exists enough seats in the flight
        int totalSeats = passengers.getAdultFormData().size() + passengers.getChildFormData().size() + passengers.getInfantFormData().size();
        if(!seatsCheck.isEnoughSeats(flightId, seatClass, totalSeats)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Not enough seats");
        }

        List<CreateTicketResponse> response = new ArrayList<>();

        response.addAll(this.createTickets(passengers.getAdultFormData(), TicketEnum.ADULT, flightId, seatClass));
        response.addAll(this.createTickets(passengers.getChildFormData(), TicketEnum.CHILD, flightId, seatClass));
        response.addAll(this.createTickets(passengers.getInfantFormData(), TicketEnum.INFANT, flightId, seatClass));

        occupySeats.occupy(flightId, totalSeats, SeatClassEnum.getEnumFromValue(seatClass));

        return response;
    }

    private List<CreateTicketResponse> createTickets(List<PassengerRequest> passengerRequests, TicketEnum ticketType, String flightId, String seatClass) {
        return passengerRequests.stream()
                .map(passengerRequest -> {
                    PassengerResponse passengerResponse = passengerService.savePassenger(passengerRequest);
                    Ticket ticket = ticketRepository.save(new Ticket(seatClass, flightId, passengerResponse.getId()));
                    double ticketPrice = calculateFlightFare.getFlightFare(ticketType, SeatClassEnum.getEnumFromValue(seatClass), flightId);
                    return new CreateTicketResponse(ticket.getId(), ticketType.getValue(), passengerResponse, seatClass, ticketPrice, flightId);
                })
                .collect(Collectors.toList());
    }
}
