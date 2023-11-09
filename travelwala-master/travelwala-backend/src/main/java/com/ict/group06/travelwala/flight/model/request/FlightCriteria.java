package com.ict.group06.travelwala.flight.model.request;

import com.ict.group06.travelwala.common.enumeration.seatclass.SeatClassEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FlightCriteria {
    private Integer adultCount;
    private Integer childCount;
    private Integer infantCount;
    private String departureCity;
    private LocalDate departureDate;
    private String arrivalCity;
    private LocalDate returnDate;
    private SeatClassEnum seatClass;
}
