package com.ict.group06.travelwala.flight.model.response;

import com.ict.group06.travelwala.flight.entity.Plane;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlaneResponse {
    private String id;
    private String manufacturer;
    private String iata;
    private String icao;
    private String name;
    private String description;
    private Integer maximumEconomicCapacity;
    private Integer maximumBusinessCapacity;

    public PlaneResponse(Plane plane) {
        this.id = plane.getId();
        this.manufacturer = plane.getManufacturer();
        this.iata = plane.getIata();
        this.icao = plane.getIcao();
        this.name = plane.getManufacturer().concat(" ").concat(this.iata);
        this.description = plane.getDescription();
        this.maximumEconomicCapacity = plane.getMaximumEconomicCapacity();
        this.maximumBusinessCapacity = plane.getMaximumBusinessCapacity();
    }
}
