package com.example.parkingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="vehicle")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isParked;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="city_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "vehicles"})
    private CityEntity city;

    // entities are loaded lazily and serialization happens before they get loaded fully --> @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="parking_facility_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "vehicles"})
    private ParkingFacilityEntity parkingFacility;

    @Column(name="type", insertable = false, updatable = false)
    private String type;
}
