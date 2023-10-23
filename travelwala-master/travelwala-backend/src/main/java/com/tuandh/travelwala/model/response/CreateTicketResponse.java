package com.tuandh.travelwala.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketResponse {
    private String id;
    private String type;
    private PassengerResponse passenger;
    private String seatClass;
    private Double amount;
    private String flightId;
}
