package com.tuandh.travelwala.flight.service.impl;

import com.tuandh.travelwala.common.enumeration.seatclass.SeatClassEnum;
import com.tuandh.travelwala.flight.entity.Flight;
import com.tuandh.travelwala.flight.exception.FlightLocationException;
import com.tuandh.travelwala.flight.exception.FlightTimeException;
import com.tuandh.travelwala.flight.exception.PassengerException;
import com.tuandh.travelwala.common.exception.RecordNotFoundException;
import com.tuandh.travelwala.flight.model.request.FlightCriteria;
import com.tuandh.travelwala.flight.service.IAvailableSeatsCheck;
import com.tuandh.travelwala.flight.service.ICalculateFlightFare;
import com.tuandh.travelwala.flight.service.IOccupySeats;
import com.tuandh.travelwala.model.response.FlightResponse;
import com.tuandh.travelwala.flight.repository.AirlineRepository;
import com.tuandh.travelwala.flight.repository.AirportRepository;
import com.tuandh.travelwala.flight.repository.FlightRepository;
import com.tuandh.travelwala.flight.repository.PlaneRepository;
import com.tuandh.travelwala.flight.service.FlightService;
import com.tuandh.travelwala.flight.model.request.FlightRequest;
import com.tuandh.travelwala.flight.model.response.SearchFlightResponse;
import com.tuandh.travelwala.ticket.enumeration.TicketEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService, IAvailableSeatsCheck, ICalculateFlightFare, IOccupySeats {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final PlaneRepository planeRepository;
    private final AirlineRepository airlineRepository;

    @Override
    public SearchFlightResponse findAll(FlightCriteria flightCriteria) {
        if(flightCriteria.getAdultCount() < 1 || flightCriteria.getChildCount() < 0 || flightCriteria.getInfantCount() < 0) {
            throw new PassengerException("Invalid seats count");
        }

        if(flightCriteria.getDepartureCity().equalsIgnoreCase(flightCriteria.getArrivalCity())) {
            throw new FlightLocationException("Departure city must differ from arrival city");
        }

        if(flightCriteria.getReturnDate() != null &&
            flightCriteria.getReturnDate().isBefore(flightCriteria.getDepartureDate()) &&
            !flightCriteria.getReturnDate().isEqual(flightCriteria.getDepartureDate())) {
            throw new FlightTimeException("Invalid return date or departure date");
        }

        List<FlightResponse> departureFlights = new ArrayList<>();
        List<FlightResponse> arrivalFlights = new ArrayList<>();

        this.flightRepository.findWithCriteria(flightCriteria).forEach(flight -> {
            // build departure flights
            if(flight.getDepartureTime().isAfter(LocalDateTime.now()) &&
                flight.getDepartureTime().toLocalDate().isEqual(flightCriteria.getDepartureDate())
            ) {
                departureFlights.add(new FlightResponse(flight));
            }

            // build return flights
            if(flightCriteria.getReturnDate() != null &&
                flight.getDepartureTime().toLocalDate().isEqual(flightCriteria.getReturnDate())) {
                arrivalFlights.add(new FlightResponse(flight));
            }
        });

        return new SearchFlightResponse(departureFlights, arrivalFlights);
    }

    @Override
    public FlightResponse findById(String id) {
        Flight flight = flightRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException("No flight found for id " + id)
        );
        return new FlightResponse(flight);
    }

    @Override
    public FlightResponse createNewFlight(FlightRequest flightRequest) {
        Flight flight = new Flight();

        flight.setCode(flightRequest.getCode());
        flight.setAdultEconomicPrice(flightRequest.getAdultEconomicPrice());
        flight.setAdultBusinessPrice(flightRequest.getAdultBusinessPrice());
        flight.setDiscountRate(flightRequest.getDiscountRate());
        flight.setArrivalAirport(airportRepository.findByName(flightRequest.getArrivalAirport()).orElseThrow(() ->
                new RecordNotFoundException("Arrival airport not found")
        ));
        flight.setDepartureAirport(airportRepository.findByName(flightRequest.getDepartureAirport()).orElseThrow(() ->
                new RecordNotFoundException("Departure airport not found")
        ));
        flight.setAirline(airlineRepository.findById(flightRequest.getAgencyId()).orElseThrow(()->
                new RecordNotFoundException("Agency not found")
        ));
        flight.setPlane(planeRepository.findById(flightRequest.getPlaneId()).orElseThrow(()->
                new RecordNotFoundException("Plane not found")
        ));
        flight.setDepartureTime(flightRequest.getDepartureTime());
        flight.setExpectedArrivalTime(flightRequest.getExpectedArrivalTime());

        return new FlightResponse(flightRepository.save(flight));
    }

    @Override
    public FlightResponse updateFlight(String id, FlightRequest flightRequest) {
        Flight flight = flightRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException("No flight found for id " + id)
        );
        flight.setCode(flightRequest.getCode());
        flight.setAdultEconomicPrice(flightRequest.getAdultEconomicPrice());
        flight.setAdultBusinessPrice(flightRequest.getAdultBusinessPrice());
        flight.setDiscountRate(flightRequest.getDiscountRate());
        flight.setArrivalAirport(airportRepository.findByName(flightRequest.getArrivalAirport()).orElseThrow(() ->
                new RecordNotFoundException("Arrival airport not found")
        ));
        flight.setDepartureAirport(airportRepository.findByName(flightRequest.getDepartureAirport()).orElseThrow(() ->
                new RecordNotFoundException("Departure airport not found")
        ));
        flight.setAirline(airlineRepository.findById(flightRequest.getAgencyId()).orElseThrow(()->
                new RecordNotFoundException("Agency not found")
        ));
        flight.setPlane(planeRepository.findById(flightRequest.getPlaneId()).orElseThrow(()->
                new RecordNotFoundException("Plane not found")
        ));
        flight.setDepartureTime(flightRequest.getDepartureTime());
        flight.setExpectedArrivalTime(flightRequest.getExpectedArrivalTime());
        return new FlightResponse(flightRepository.save(flight));
    }

    @Override
    public boolean isEnoughSeats(String flightId, String seatClass, int numberOfSeats) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(
                () -> new RecordNotFoundException("No flight found for id " + flightId)
        );

        if(seatClass.equals(SeatClassEnum.ECONOMY.getValue())) {
            return flight.getPlane().getMaximumEconomicCapacity() - flight.getOccupiedEconomicSeats() > numberOfSeats;
        }
        else if(seatClass.equals(SeatClassEnum.BUSINESS.getValue())) {
            return flight.getPlane().getMaximumBusinessCapacity() - flight.getOccupiedBusinessSeats() > numberOfSeats;
        }

        return false;
    }

    @Override
    public Double getFlightFare(TicketEnum ticketType, SeatClassEnum seatClass, String flightId) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(
                () -> new RecordNotFoundException("No flight found for id " + flightId)
        );

        double basePrice = 0D;
        if(seatClass == SeatClassEnum.BUSINESS) {
            basePrice = flight.getAdultBusinessPrice();
        }
        else if(seatClass == SeatClassEnum.ECONOMY) {
            basePrice = flight.getAdultEconomicPrice();
        }

        switch (ticketType) {
            case ADULT:
                return basePrice;
            case CHILD:
                return basePrice * flight.getAirline().getChildPriceRate();
            default:
                return 0D;
        }
    }

    @Override
    public void occupy(String flightId, int numberOfSeats, SeatClassEnum seatClass) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(
                () -> new RecordNotFoundException("No flight found for id " + flightId)
        );

        if(seatClass == SeatClassEnum.BUSINESS) {
            flight.setOccupiedBusinessSeats(flight.getOccupiedBusinessSeats() + numberOfSeats);
        }
        else if(seatClass == SeatClassEnum.ECONOMY) {
            flight.setOccupiedEconomicSeats(flight.getOccupiedEconomicSeats() + numberOfSeats);
        }

        flightRepository.save(flight);
    }
}
