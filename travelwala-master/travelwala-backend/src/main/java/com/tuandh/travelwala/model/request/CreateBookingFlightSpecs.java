package com.tuandh.travelwala.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingFlightSpecs {
    @Valid
    private TravellerSpecs travellerSpecs;

    @Valid
    private FlightProductSpecs flightProductSpecs;

    public int getAdultNumberOfSeats() {
        return this.flightProductSpecs.getAdultCount();
    }

    public int getChildNumberOfSeats() {
        return this.flightProductSpecs.getChildCount();
    }

    public int getInfantNumberOfSeats() {
        return this.flightProductSpecs.getInfantCount();
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TravellerSpecs {
        private List<@Valid PassengerRequest> adultFormData;
        private List<@Valid PassengerRequest> childFormData;
        private List<@Valid PassengerRequest> infantFormData;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FlightProductSpecs {
        @NotBlank(message = "Please provide departure flight id to be booked")
        private String departureFlightId;

        private String returnFlightId;

        private String seatClass;

        @PositiveOrZero(message = "Number of seats of adult must not be negative")
        private Integer adultCount;

        @PositiveOrZero(message = "Number of seats of child must not be negative")
        private Integer childCount;

        @PositiveOrZero(message = "Number of seats of infant must not be negative")
        private Integer infantCount;
    }
}
