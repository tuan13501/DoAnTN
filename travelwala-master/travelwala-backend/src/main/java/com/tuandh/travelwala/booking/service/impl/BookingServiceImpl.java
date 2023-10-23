package com.tuandh.travelwala.booking.service.impl;

import com.tuandh.travelwala.accounting.service.ICreateInvoice;
import com.tuandh.travelwala.booking.entity.Booking;
import com.tuandh.travelwala.booking.entity.FlightBookingLineItem;
import com.tuandh.travelwala.booking.exception.BookingRequestException;
import com.tuandh.travelwala.booking.model.response.CreateBookingResponse;
import com.tuandh.travelwala.booking.repository.BookingRepository;
import com.tuandh.travelwala.booking.service.ICreateBooking;
import com.tuandh.travelwala.contact.service.ISaveContact;
import com.tuandh.travelwala.model.request.CreateBookingRequest;
import com.tuandh.travelwala.model.response.ContactResponse;
import com.tuandh.travelwala.model.response.CreateTicketResponse;
import com.tuandh.travelwala.ticket.enumeration.TicketEnum;
import com.tuandh.travelwala.ticket.service.ICreateTicket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements ICreateBooking {
    private final BookingRepository bookingRepository;
    private final ICreateTicket createTicket;
    private final ISaveContact saveContact;
    private final ICreateInvoice createInvoice;

    @Override
    public CreateBookingResponse createBooking(CreateBookingRequest bookingRequest) {
        ContactResponse contactResponse = saveContact.saveContact(bookingRequest.getContactRequest());

        // check if the request number of seats are the same to number of passengers
        if(bookingRequest.getCreateBookingFlightSpecs().getAdultNumberOfSeats() != bookingRequest.getCreateBookingFlightSpecs().getTravellerSpecs().getAdultFormData().size() ||
            bookingRequest.getCreateBookingFlightSpecs().getChildNumberOfSeats() != bookingRequest.getCreateBookingFlightSpecs().getTravellerSpecs().getChildFormData().size() ||
            bookingRequest.getCreateBookingFlightSpecs().getInfantNumberOfSeats() != bookingRequest.getCreateBookingFlightSpecs().getTravellerSpecs().getInfantFormData().size()
        ) {
            throw new BookingRequestException("Number of seats and number of travellers are mismatch");
        }

        // create tickets for departure flight
        List<CreateTicketResponse> ticketsResponse = createTicket.createTickets(
                bookingRequest.getCreateBookingFlightSpecs().getTravellerSpecs(),
                bookingRequest.getCreateBookingFlightSpecs().getFlightProductSpecs().getDepartureFlightId(),
                bookingRequest.getCreateBookingFlightSpecs().getFlightProductSpecs().getSeatClass()
        );

        // create tickets for return flight
        String returnFlightId = bookingRequest.getCreateBookingFlightSpecs().getFlightProductSpecs().getReturnFlightId();
        if(returnFlightId != null && !returnFlightId.isBlank()) {
            ticketsResponse.addAll(createTicket.createTickets(
                    bookingRequest.getCreateBookingFlightSpecs().getTravellerSpecs(),
                    returnFlightId,
                    bookingRequest.getCreateBookingFlightSpecs().getFlightProductSpecs().getSeatClass()
            ));
        }

        Booking savedBooking = bookingRepository.save(new Booking(
                ticketsResponse.stream()
                        .map(ticket -> new FlightBookingLineItem(ticket.getId(), ticket.getAmount()))
                        .collect(Collectors.toList()),
                contactResponse.getId()
        ));

        double totalAmount = savedBooking.getBookingLineItems()
                .stream()
                .map(bookingLineItem -> bookingLineItem.getUnitPrice() * bookingLineItem.getQuantity())
                .reduce(0D, Double::sum);

        String invoiceId = createInvoice.createInvoice(savedBooking.getId(), totalAmount).getId();

        return new CreateBookingResponse(
                savedBooking.getId(),
                contactResponse,
                ticketsResponse.stream().filter(ticket -> ticket.getType().equals(TicketEnum.ADULT.getValue())).collect(Collectors.toList()),
                ticketsResponse.stream().filter(ticket -> ticket.getType().equals(TicketEnum.CHILD.getValue())).collect(Collectors.toList()),
                ticketsResponse.stream().filter(ticket -> ticket.getType().equals(TicketEnum.INFANT.getValue())).collect(Collectors.toList()),
                invoiceId
        );

    }
}
