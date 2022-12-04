package com.example.parkingapp.model.dtos;

import com.example.parkingapp.model.CarParkEntity;
import com.example.parkingapp.model.CityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarParkingFacilityCityDTO {
    private CarParkEntity carPark;
    private CityEntity city;
}
