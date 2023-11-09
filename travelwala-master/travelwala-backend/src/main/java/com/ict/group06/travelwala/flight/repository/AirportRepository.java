package com.ict.group06.travelwala.flight.repository;

import com.ict.group06.travelwala.flight.entity.Airport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends MongoRepository<Airport, String> {
    Optional<Airport> findByName(String name);
    List<Airport> findAllByCountry(String country);
}
