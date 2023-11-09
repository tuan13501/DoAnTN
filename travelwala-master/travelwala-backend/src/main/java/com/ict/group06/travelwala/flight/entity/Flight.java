package com.ict.group06.travelwala.flight.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document("flights")
@Getter
@Setter
@NoArgsConstructor
public class Flight {
    @Id
    private String id;

    @Field("code")
    @Setter
    @Indexed(unique = true, name = "flight_code_generator")
    private String code;

    @Field("adult_economic_price")
    private Double adultEconomicPrice;

    @Field("adult_business_price")
    private Double adultBusinessPrice;

    @Field("discount_rate")
    private Double discountRate;

    @Field("departure_airports")
    private Airport departureAirport;

    @Field("arrival_airports")
    private Airport arrivalAirport;

    @Field("departure_time")
    private LocalDateTime departureTime;

    @Field("expected_arrival_time")
    private LocalDateTime expectedArrivalTime;

    @Field("occupied_economic_seats")
    private Integer occupiedEconomicSeats;

    @Field("occupied_business_seats")
    private Integer occupiedBusinessSeats;

    @Field("airline")
    private Airline airline;

    @Field("plane")
    private Plane plane;

    public Flight(Double adultEconomicPrice, Double adultBusinessPrice, Double discountRate,
                  Airport departureAirport, Airport arrivalAirport, LocalDateTime departureTime,
                  LocalDateTime expectedArrivalTime, Airline airline, Plane plane) {
        this.adultEconomicPrice = adultEconomicPrice;
        this.adultBusinessPrice = adultBusinessPrice;
        this.discountRate = discountRate;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.expectedArrivalTime = expectedArrivalTime;
        this.occupiedEconomicSeats = 0;
        this.occupiedBusinessSeats = 0;
        this.airline = airline;
        this.plane = plane;
    }

    public Flight(Double adultEconomicPrice, Double adultBusinessPrice, Double discountRate,
                  Airport departureAirport, Airport arrivalAirport, LocalDateTime departureTime,
                  LocalDateTime expectedArrivalTime, Integer occupiedEconomicSeats, Integer occupiedBusinessSeats,
                  Airline airline, Plane plane) {
        this.adultEconomicPrice = adultEconomicPrice;
        this.adultBusinessPrice = adultBusinessPrice;
        this.discountRate = discountRate;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.expectedArrivalTime = expectedArrivalTime;
        this.occupiedEconomicSeats = occupiedEconomicSeats;
        this.occupiedBusinessSeats = occupiedBusinessSeats;
        this.airline = airline;
        this.plane = plane;
    }
}
