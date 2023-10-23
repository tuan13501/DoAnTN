import {
  ADD_ADULT,
  ADD_CHILD,
  ADD_INFANT,
  REMOVE_ADULT,
  REMOVE_CHILD,
  REMOVE_INFANT,
  SET_DEPARTURE_DATE,
  SET_RETURN_DATE,
  SET_TO_AIRPORT,
  SET_FROM_AIRPORT,
  SET_SEAT_CLASS,
  SET_ROUND_TRIP
} from "./constants";

export const addAdult = payload => ({
  type: ADD_ADULT,
  payload
})

export const addChild = payload => ({
  type: ADD_CHILD,
  payload
})

export const addInfant = payload => ({
  type: ADD_INFANT,
  payload
})

export const removeAdult = payload => ({
  type: REMOVE_ADULT,
  payload
})

export const removeChild = payload => ({
  type: REMOVE_CHILD,
  payload
})

export const removeInfant = payload => ({
  type: REMOVE_INFANT,
  payload
})

export const setDepartureDate = payload => ({
  type: SET_DEPARTURE_DATE,
  payload
})

export const setReturnDate = payload => ({
  type: SET_RETURN_DATE,
  payload
})

export const setFromAirport = payload => ({
  type: SET_FROM_AIRPORT,
  payload
})

export const setToAirport = payload => ({
  type: SET_TO_AIRPORT,
  payload
})

export const setSeatClass = payload => ({
    type: SET_SEAT_CLASS,
    payload
})

export const setRoundTrip = payload => ({
  type: SET_ROUND_TRIP,
  payload
})