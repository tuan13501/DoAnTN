package com.tuandh.travelwala.flight.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FlightRequest {
    private String code;
    private Double adultEconomicPrice;
    private Double adultBusinessPrice;
    private Double discountRate;
    private String departureAirport;
    private String arrivalAirport;
    private String agencyId;
    private String planeId;
    private LocalDateTime departureTime;
    private LocalDateTime expectedArrivalTime;
}
