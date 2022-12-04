package com.example.parkingapp.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BikeRack")
public class BikeRackEntity extends ParkingFacilityEntity{
}
