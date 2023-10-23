package com.tuandh.travelwala.booking.repository;

import com.tuandh.travelwala.booking.entity.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, String> {
}
