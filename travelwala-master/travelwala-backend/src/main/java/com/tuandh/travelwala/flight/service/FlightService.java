package com.tuandh.travelwala.flight.service;

import com.tuandh.travelwala.flight.model.request.FlightCriteria;
import com.tuandh.travelwala.model.response.FlightResponse;
import com.tuandh.travelwala.flight.model.request.FlightRequest;
import com.tuandh.travelwala.flight.model.response.SearchFlightResponse;

public interface FlightService {
    SearchFlightResponse findAll(FlightCriteria flightCriteria);
    FlightResponse findById(String id);
    FlightResponse createNewFlight(FlightRequest flightRequest);
    FlightResponse updateFlight(String id, FlightRequest flightRequest);
}
