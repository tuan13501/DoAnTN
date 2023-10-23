package com.tuandh.travelwala.passenger.service;

import com.tuandh.travelwala.model.request.PassengerRequest;
import com.tuandh.travelwala.model.response.PassengerResponse;

public interface PassengerService {
    PassengerResponse savePassenger(PassengerRequest passengerRequest);
}
