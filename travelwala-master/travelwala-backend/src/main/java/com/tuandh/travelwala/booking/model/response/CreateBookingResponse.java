package com.tuandh.travelwala.booking.model.response;

import com.tuandh.travelwala.model.response.ContactResponse;
import com.tuandh.travelwala.model.response.CreateTicketResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingResponse {
    private String bookingId;
    private ContactResponse bookingContact;
    private List<CreateTicketResponse> adultTickets;
    private List<CreateTicketResponse> childTickets;
    private List<CreateTicketResponse> infantTickets;
    private String invoiceId;
}
