package com.tuandh.travelwala.flight.model.response;

import com.tuandh.travelwala.flight.entity.Airport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirportResponse {
    private String id;
    private String name;
    private String iata;
    private String city;
    private String country;
    private Double latitude;
    private Double longitude;

    public AirportResponse(Airport airport) {
        this(airport.getId(), airport.getName(), airport.getIata(),
            airport.getCity(), airport.getCountry(),
            airport.getLatitude(), airport.getLongitude()
        );
    }
}
