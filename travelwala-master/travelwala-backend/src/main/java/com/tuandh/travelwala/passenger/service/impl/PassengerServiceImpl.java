package com.tuandh.travelwala.passenger.service.impl;

import com.tuandh.travelwala.mapstruct.mappers.PassengerMapper;
import com.tuandh.travelwala.model.request.PassengerRequest;
import com.tuandh.travelwala.model.response.PassengerResponse;
import com.tuandh.travelwala.passenger.entity.Passenger;
import com.tuandh.travelwala.passenger.repository.PassengerRepository;
import com.tuandh.travelwala.passenger.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {
    private PassengerMapper mapper = Mappers.getMapper(PassengerMapper.class);
    private final PassengerRepository passengerRepository;

    @Override
    public PassengerResponse savePassenger(PassengerRequest passengerRequest) {
        Passenger passenger = mapper.passengerRequestToPassenger(passengerRequest);
        return mapper.passengerToPassengerResponse(passengerRepository.save(passenger));
    }
}
