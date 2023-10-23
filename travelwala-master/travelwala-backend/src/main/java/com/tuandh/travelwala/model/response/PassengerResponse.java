package com.tuandh.travelwala.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PassengerResponse {
    private String id;
    private String title;
    private String firstName;
    private String lastName;
    private String dob;
    private String nationality;
}
