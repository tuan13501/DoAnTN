package com.tuandh.travelwala.flight.repository;

import com.tuandh.travelwala.flight.entity.Flight;
import com.tuandh.travelwala.flight.model.request.FlightCriteria;
import com.tuandh.travelwala.common.repository.WalaRepository;

import java.util.List;

public interface FlightRepository extends WalaRepository<Flight, String> {
    List<Flight> findWithCriteria(FlightCriteria flightCriteria);
}
