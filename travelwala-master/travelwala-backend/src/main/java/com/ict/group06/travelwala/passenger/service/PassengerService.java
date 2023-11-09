package com.ict.group06.travelwala.passenger.service;

import com.ict.group06.travelwala.model.request.PassengerRequest;
import com.ict.group06.travelwala.model.response.PassengerResponse;

public interface PassengerService {
    PassengerResponse savePassenger(PassengerRequest passengerRequest);
}
