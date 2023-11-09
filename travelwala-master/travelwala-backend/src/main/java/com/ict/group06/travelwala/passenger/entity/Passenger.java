package com.ict.group06.travelwala.passenger.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("passengers")
@Data
public class Passenger {
    @Id
    private String id;

    @Field("value")
    private String title;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    @Field("dob")
    private String dob;

    @Field("nationality")
    private String nationality;
}
