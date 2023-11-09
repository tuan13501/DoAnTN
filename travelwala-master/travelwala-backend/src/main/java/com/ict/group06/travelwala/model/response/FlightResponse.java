package com.ict.group06.travelwala.model.response;

import com.ict.group06.travelwala.flight.entity.Flight;
import com.ict.group06.travelwala.flight.model.response.AirlineResponse;
import com.ict.group06.travelwala.flight.model.response.PlaneResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponse {
    private String id;
    private String code;
    private Double adultEconomicPrice;
    private Double adultBusinessPrice;
    private Double discountRate;
    private String departureCity;
    private String arrivalCity;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime expectedArrivalTime;
    private Integer occupiedEconomicSeats;
    private Integer occupiedBusinessSeats;
    private PlaneResponse plane;
    private AirlineResponse airline;

    public FlightResponse(Flight flight) {
        this.id = flight.getId();
        this.code = flight.getCode();
        this.adultBusinessPrice = flight.getAdultBusinessPrice();
        this.adultEconomicPrice = flight.getAdultEconomicPrice();
        this.discountRate = flight.getDiscountRate();
        this.departureCity = flight.getDepartureAirport().getCity();
        this.arrivalCity = flight.getArrivalAirport().getCity();
        this.departureAirport = flight.getDepartureAirport().getName();
        this.arrivalAirport = flight.getArrivalAirport().getName();
        this.departureTime = flight.getDepartureTime();
        this.expectedArrivalTime = flight.getExpectedArrivalTime();
        this.occupiedEconomicSeats = flight.getOccupiedEconomicSeats();
        this.occupiedBusinessSeats = flight.getOccupiedBusinessSeats();
        this.plane = new PlaneResponse(flight.getPlane());
        this.airline = new AirlineResponse(flight.getAirline());
    }
}
