package com.ict.group06.travelwala.mapstruct.mappers;

import com.ict.group06.travelwala.model.request.PassengerRequest;
import com.ict.group06.travelwala.model.response.PassengerResponse;
import com.ict.group06.travelwala.passenger.entity.Passenger;
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
