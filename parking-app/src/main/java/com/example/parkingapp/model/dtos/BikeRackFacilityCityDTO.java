package com.example.parkingapp.model.dtos;

import com.example.parkingapp.model.BikeRackEntity;
import com.example.parkingapp.model.CityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BikeRackFacilityCityDTO {
    BikeRackEntity bikeRack;
    CityEntity city;
}
