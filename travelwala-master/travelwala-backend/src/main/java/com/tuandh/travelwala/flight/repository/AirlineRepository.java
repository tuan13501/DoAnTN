package com.tuandh.travelwala.flight.repository;

import com.tuandh.travelwala.flight.entity.Airline;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AirlineRepository extends MongoRepository<Airline, String> {
}
