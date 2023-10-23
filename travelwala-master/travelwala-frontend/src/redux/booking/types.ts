import { FlightResponse } from "../../api/flight/FlightService.types";
import { PassengerDetail } from "../../api/passenger/passenger.types";

export type BookingState = {
  departureFlight: FlightResponse;
  returnFlight?: FlightResponse;

  // TODO: create type ContactResponse in directory /api/contact
  passengers: PassengerDetail[]
  contactDetail: PassengerDetail
  // TODO: create type PassengerResponse in directory /api/response
};
