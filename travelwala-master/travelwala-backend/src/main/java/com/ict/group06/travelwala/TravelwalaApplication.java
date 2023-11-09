package com.ict.group06.travelwala;

import com.ict.group06.travelwala.flight.entity.Airline;
import com.ict.group06.travelwala.flight.entity.Airport;
import com.ict.group06.travelwala.flight.entity.Flight;
import com.ict.group06.travelwala.flight.entity.Plane;
import com.ict.group06.travelwala.flight.repository.AirlineRepository;
import com.ict.group06.travelwala.flight.repository.AirportRepository;
import com.ict.group06.travelwala.flight.repository.FlightRepository;
import com.ict.group06.travelwala.flight.repository.PlaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SpringBootApplication
public class TravelwalaApplication {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private PlaneRepository planeRepository;
    @Autowired
    private AirlineRepository airlineRepository;

    public static void main(String[] args) {
        SpringApplication.run(TravelwalaApplication.class, args);
    }

//    @PostConstruct
//    private void generateFlightData() {
//        List<Plane> planes = planeRepository.findAll();
//        List<Airline> airlines = airlineRepository.findAll();
//        List<Airport> airports = airportRepository.findAll();
//
//        List<Airport> airportInVietnam = airports.stream().filter(airport -> airport.getCountry().equals("Vietnam")).collect(Collectors.toList());
//
//        Random random = new Random();
//
//        // generate 10k flight data
//        for (int i = 0; i < 10000; i++) {
//            double economicPrice = Math.abs(random.nextDouble());
//            double businessPrice = Math.abs(random.nextDouble());
//            double discountRate = random.nextDouble() % 25D;
//
//            Airport departureAirport = airportInVietnam.get(random.nextInt(airportInVietnam.size()));
//            Airport arrivalAirport = airportInVietnam.get(random.nextInt(airportInVietnam.size()));
//
//            Plane plane = planes.get(random.nextInt(planes.size()));
//            Airline airline = airlines.get(random.nextInt(airlines.size()));
//
//            LocalDateTime currentDateTime = LocalDateTime.now();
//
//            // find airports in Vietnam
//            Flight newFLight = new Flight(
//                150000.0, 250000.0, 25.0,
//                departureAirport, arrivalAirport,
//                currentDateTime.plusDays(random.nextInt(90)), currentDateTime.plusDays(random.nextInt(90)),
//                random.nextInt(plane.getMaximumEconomicCapacity()), random.nextInt(plane.getMaximumBusinessCapacity()),
//                airline, plane
//            );
//
//            flightRepository.save(newFLight);
//        }
//    }

}
