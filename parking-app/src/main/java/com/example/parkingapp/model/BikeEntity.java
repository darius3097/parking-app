package com.example.parkingapp.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Bike")
public class BikeEntity extends VehicleEntity{
}
