package com.ict.group06.travelwala.flight.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(value = "airlines")
@Getter
@NoArgsConstructor
public class Airline {
    @Id
    private String id;

    @Field("name")
    @Setter
    private String name;

    @Field("alias")
    @Setter
    private String alias;

    @Field("iata")
    @Setter
    private String iata;

    @Field("icao")
    @Setter
    private String icao;

    @Field("call_sign")
    @Setter
    private String callSign;

    @Field("country")
    @Setter
    private String country;

    @Field("active")
    @Setter
    private boolean active;

    @Field("child_price_rate")
    @Setter
    private Double childPriceRate;

    public Airline(String name, String alias,
                   String iata, String icao, String callSign,
                   String country, boolean active, Double childPriceRate) {
        this.name = name;
        this.alias = alias;
        this.iata = iata;
        this.icao = icao;
        this.callSign = callSign;
        this.country = country;
        this.active = active;
        this.childPriceRate = childPriceRate;
    }
}
