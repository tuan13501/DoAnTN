package com.ict.group06.travelwala.ticket.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
public class Ticket {
    @Id
    private String id;

    @Field("seat_class")
    @Setter
    private String seatClass;

    @Field("flight_id")
    @Setter
    private String flightId;

    @Field("passenger_id")
    @Setter
    private String passengerId;

    public Ticket(String seatClass, String flightId, String passengerId) {
        this.seatClass = seatClass;
        this.flightId = flightId;
        this.passengerId = passengerId;
    }
}
