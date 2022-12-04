package com.example.parkingapp.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingFacilityDTO {
    private Long id;
    private String name;
    private Integer capacity;
    private Integer availableCapacity;
}
