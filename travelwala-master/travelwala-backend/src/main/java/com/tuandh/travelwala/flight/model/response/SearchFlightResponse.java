package com.tuandh.travelwala.flight.model.response;

import com.tuandh.travelwala.model.response.FlightResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchFlightResponse {
    private List<FlightResponse> departureFlights;
    private List<FlightResponse> returnFlights;
}
