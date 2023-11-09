package com.ict.group06.travelwala.booking.model.response;

import com.ict.group06.travelwala.model.response.ContactResponse;
import com.ict.group06.travelwala.model.response.CreateTicketResponse;
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
