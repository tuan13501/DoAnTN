import { ADD_ADULT, ADD_CHILD, ADD_INFANT, REMOVE_ADULT, REMOVE_CHILD, REMOVE_INFANT, SET_RETURN_DATE, SET_DEPARTURE_DATE, SET_TO_AIRPORT, SET_FROM_AIRPORT, SET_SEAT_CLASS, SET_ROUND_TRIP} from "./constants";

const initState = {
  passenger: {
    adult: 1,
    child: 0,
    infant: 0
  },
  airport: {
    from: "Hanoi",
    to: "Ho Chi Minh City"
  },
  date: {
    departureDate: new Date(),
    returnDate: null
  },
  seatClass: "Economy",
  roundTrip: false
}
const rootReducer = (state = initState, action) => {
  switch (action.type) {
    case ADD_ADULT:
      return {
        ...state,
        passenger: {
          ...state.passenger,
          adult: state.passenger.adult + 1
        }
      }
    case ADD_CHILD:
      return {
        ...state,
        passenger: {
          ...state.passenger,
          child: state.passenger.child + 1
        }
      }
    case ADD_INFANT:
      return {
        ...state,
        passenger: {
          ...state.passenger,
          infant: state.passenger.infant + 1
        }
      }
    case REMOVE_ADULT:
      return {
        ...state,
        passenger: {
          ...state.passenger,
          adult: state.passenger.adult >= 2 ? state.passenger.adult - 1 : state.passenger.adult
        }
      }
    case REMOVE_CHILD:
      return {
        ...state,
        passenger: {
          ...state.passenger,
          child: state.passenger.child >= 1 ? state.passenger.child - 1 : state.passenger.child
        }
      }
    case REMOVE_INFANT:
      return {
        ...state,
        passenger: {
          ...state.passenger,
          infant: state.passenger.infant >= 1 ? state.passenger.infant - 1 : state.passenger.infant
        }
      }
    case SET_FROM_AIRPORT:
      return {
        ...state,
        airport: {
          ...state.airport,
          from: action.payload
        }
      }
    case SET_TO_AIRPORT:
      return {
        ...state,
        airport: {
          ...state.airport,
          to: action.payload
        }
      }
    case SET_DEPARTURE_DATE:
      return {
        ...state,
        date: {
          ...state.date,
          departureDate: action.payload
        }
      }
    case SET_RETURN_DATE:
      return {
        ...state,
        date: {
          ...state.date,
          returnDate: action.payload
        }
      }
    case SET_SEAT_CLASS:
      return {
        ...state,
        seatClass: action.payload
      }
    case SET_ROUND_TRIP:
      return {
        ...state,
        roundTrip: action.payload
      }
    default:
      return state
  }
}

export default rootReducer;