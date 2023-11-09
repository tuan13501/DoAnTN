package com.ict.group06.travelwala.flight.repository;

import com.ict.group06.travelwala.flight.entity.Airline;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AirlineRepository extends MongoRepository<Airline, String> {
}
