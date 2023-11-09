package com.ict.group06.travelwala.flight.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "planes")
@Getter
@NoArgsConstructor
public class Plane {
    @Id
    private String id;

    @Field("manufacturer")
    @Setter
    private String manufacturer;

    @Field("iata")
    @Setter
    private String iata;

    @Field("icao")
    @Setter
    private String icao;

    @Field("description")
    @Setter
    private String description;

    @Field("maximum_economic_capacity")
    @Setter
    private Integer maximumEconomicCapacity;

    @Field("maximum_business_capacity")
    @Setter
    private Integer maximumBusinessCapacity;

    public Plane(String manufacturer, String iata, String icao,
                 String description, Integer maximumEconomicCapacity, Integer maximumBusinessCapacity) {
        this.manufacturer = manufacturer;
        this.iata = iata;
        this.icao = icao;
        this.description = description;
        this.maximumEconomicCapacity = maximumEconomicCapacity;
        this.maximumBusinessCapacity = maximumBusinessCapacity;
    }

    public String getName() {
        return this.manufacturer.concat(this.iata);
    }
}
