package com.tuandh.travelwala.mapstruct.mappers;

import com.tuandh.travelwala.model.request.PassengerRequest;
import com.tuandh.travelwala.model.response.PassengerResponse;
import com.tuandh.travelwala.passenger.entity.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PassengerMapper {
    @Mapping(
        target = "dob",
//        expression = "java(LocalDate.of(passengerRequest.getDob().getYear(), passengerRequest.getDob().getMonth(), passengerRequest.getDob().getDay()).toString() )",
        expression = "java( passengerRequest.getDob().toString() )"
    )
    Passenger passengerRequestToPassenger(PassengerRequest passengerRequest);
    PassengerResponse passengerToPassengerResponse(Passenger passenger);
}
