package com.example.parkingapp.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Car")
public class CarEntity extends VehicleEntity{
}
