package com.tuandh.travelwala.flight.repository;

import com.tuandh.travelwala.flight.entity.Plane;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaneRepository extends MongoRepository<Plane, String> {
}
