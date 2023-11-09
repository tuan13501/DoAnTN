package com.ict.group06.travelwala.flight.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("airports")
@Getter
@NoArgsConstructor
public class Airport {
    @Id
    private String id;

    @Field("name")
    @Indexed(unique = true, name = "airport_name_unique")
    @Setter
    private String name;

    @Field("iata")
    @Setter
    private String iata;

    @Field("city")
    @Setter
    private String city;

    @Field("country")
    @Setter
    private String country;

    @Field("latitude")
    @Setter
    private Double latitude;

    @Field("longitude")
    @Setter
    private Double longitude;

    public Airport(String name, String iata, String city, String country, Double latitude, Double longitude) {
        this.name = name;
        this.iata = iata;
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
