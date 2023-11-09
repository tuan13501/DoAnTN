package com.ict.group06.travelwala.booking.service.impl;

import com.ict.group06.travelwala.accounting.service.ICreateInvoice;
import com.ict.group06.travelwala.booking.entity.Booking;
import com.ict.group06.travelwala.booking.entity.FlightBookingLineItem;
import com.ict.group06.travelwala.booking.exception.BookingRequestException;
import com.ict.group06.travelwala.booking.model.response.CreateBookingResponse;
import com.ict.group06.travelwala.booking.repository.BookingRepository;
import com.ict.group06.travelwala.booking.service.ICreateBooking;
import com.ict.group06.travelwala.contact.service.ISaveContact;
import com.ict.group06.travelwala.model.request.CreateBookingRequest;
import com.ict.group06.travelwala.model.response.ContactResponse;
import com.ict.group06.travelwala.model.response.CreateTicketResponse;
import com.ict.group06.travelwala.ticket.enumeration.TicketEnum;
import com.ict.group06.travelwala.ticket.service.ICreateTicket;
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
