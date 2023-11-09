package com.ict.group06.travelwala.flight.repository;

import com.ict.group06.travelwala.flight.entity.Plane;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaneRepository extends MongoRepository<Plane, String> {
}
