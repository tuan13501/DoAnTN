export type FlightCriteria = {
  adultCount: number;
  childCount: number;
  infantCount: number;
  departureCity: string;
  departureDate: string;
  arrivalCity: string;
  returnDate: string | null;
  seatClass: string;
};

export type SearchFlightResponse = {
  departureFlights?: FlightResponse[];
  returnFlights?: FlightResponse[];
};

export type FlightResponse = {
  id: string;
  code: string;
  adultEconomicPrice: number;
  adultBusinessPrice: number;
  discountRate?: number | null;
  departureCity: string;
  arrivalCity: string;
  departureAirport: string;
  arrivalAirport: string;
  departureTime: string;
  expectedArrivalTime: string;
  occupiedEconomicSeats: number;
  occupiedBusinessSeats: number;
  plane: PlaneResponse;
  airline: AirlineResponse;
};

export type AirlineResponse = {
  id: string;
  name: string;
  alias?: string | null;
  iata: string | null;
  icao: string | null;
  callSign: string;
  country: string;
  active: boolean;
  childPriceRate: number;
  imageLink?: string | null;
};

export type PlaneResponse = {
  id: string;
  manufacturer: string;
  code: string;
  name: string;
  maximumEconomicCapacity: number;
  maximumBusinessCapacity: number;
};
