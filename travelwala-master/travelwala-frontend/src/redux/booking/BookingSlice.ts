import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { BookingState } from "./types";
import { FlightResponse } from "../../api/flight/FlightService.types";
import { PassengerDetail } from "../../api/passenger/passenger.types";

const initialState: BookingState = {
  departureFlight: {} as FlightResponse,
  returnFlight: {} as FlightResponse,
  passengers: [] as PassengerDetail[],
  contactDetail: {} as PassengerDetail
};

const bookingSlice = createSlice({
  name: "bookingSlice",
  initialState: initialState,
  reducers: {
    chooseDepartureFlightForBooking: (
      state,
      action: PayloadAction<FlightResponse>
    ) => {
      state.departureFlight = action.payload;
    },
    chooseReturnFlightForBooking: (
      state,
      action: PayloadAction<FlightResponse>
    ) => {
      state.returnFlight = action.payload;
    },
    choosePassengerForBooking: (
      state,
      action: PayloadAction<PassengerDetail[]>
    ) => {
      state.passengers = action.payload
    },
    chooseContactDetailForBooking: (
      state,
      action: PayloadAction<PassengerDetail>
    ) => {
      state.contactDetail = action.payload
    }
  },
});

export default bookingSlice.reducer;
export const { chooseDepartureFlightForBooking, chooseReturnFlightForBooking, choosePassengerForBooking, chooseContactDetailForBooking} =
  bookingSlice.actions;
