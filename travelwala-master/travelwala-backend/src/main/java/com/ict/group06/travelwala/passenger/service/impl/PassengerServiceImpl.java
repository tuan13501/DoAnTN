package com.ict.group06.travelwala.passenger.service.impl;

import com.ict.group06.travelwala.mapstruct.mappers.PassengerMapper;
import com.ict.group06.travelwala.model.request.PassengerRequest;
import com.ict.group06.travelwala.model.response.PassengerResponse;
import com.ict.group06.travelwala.passenger.entity.Passenger;
import com.ict.group06.travelwala.passenger.repository.PassengerRepository;
import com.ict.group06.travelwala.passenger.service.PassengerService;
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
