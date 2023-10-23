package com.tuandh.travelwala.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingRequest {
    @Valid
    private CreateBookingFlightSpecs createBookingFlightSpecs;

    @JsonProperty("bookingContact")
    @Valid
    @NotNull(message = "Please provide booking contact")
    private ContactRequest contactRequest;
}
