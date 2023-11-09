package com.ict.group06.travelwala.flight.repository;

import com.ict.group06.travelwala.flight.entity.Flight;
import com.ict.group06.travelwala.flight.model.request.FlightCriteria;
import com.ict.group06.travelwala.common.repository.WalaRepository;

import java.util.List;

public interface FlightRepository extends WalaRepository<Flight, String> {
    List<Flight> findWithCriteria(FlightCriteria flightCriteria);
}
