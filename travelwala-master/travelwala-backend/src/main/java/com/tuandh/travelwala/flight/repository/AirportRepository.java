package com.tuandh.travelwala.flight.repository;

import com.tuandh.travelwala.flight.entity.Airport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends MongoRepository<Airport, String> {
    Optional<Airport> findByName(String name);
}
