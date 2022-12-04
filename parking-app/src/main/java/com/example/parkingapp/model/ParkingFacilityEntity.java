package com.example.parkingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="parking_facility")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
public class ParkingFacilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 256)
    @NotBlank(message = "Name is mandatory")
    private String name;

    private Integer capacity;

    private Integer availableCapacity;

    // entities are loaded lazily and serialization happens before they get loaded fully --> @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"
    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="city_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "parkingFacilities"})
    private CityEntity city;

    @OneToMany(mappedBy = "parkingFacility", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("parkingFacility")
    private List<VehicleEntity> vehicles = new ArrayList<>();

    @Column(name="type", insertable = false, updatable = false)
    private String type;
}
