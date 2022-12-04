package com.example.parkingapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="city")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 256)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Length(max = 4)
    @NotBlank(message = "Code is mandatory")
    private String code;

    @OneToMany(mappedBy = "city", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("city")
    private List<ParkingFacilityEntity> parkingFacilities = new ArrayList<>();

    @OneToMany(mappedBy = "city", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("city")
    private List<VehicleEntity> vehicles = new ArrayList<>();
}

