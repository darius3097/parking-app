package com.example.parkingapp.controllers;

import com.example.parkingapp.model.BikeRackEntity;
import com.example.parkingapp.model.CarParkEntity;
import com.example.parkingapp.model.ParkingFacilityEntity;
import com.example.parkingapp.model.dtos.BikeRackFacilityCityDTO;
import com.example.parkingapp.model.dtos.CarParkingFacilityCityDTO;
import com.example.parkingapp.services.ParkingFacilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/parking-facility", produces="application/json")
@RequiredArgsConstructor
@Slf4j
public class ParkingFacilityController {

    private final ParkingFacilityService parkingFacilityService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ParkingFacilityEntity> getParkingFacilityById(@PathVariable("id") Long parkingFacilityId){
        return ResponseEntity.ok(parkingFacilityService.getParkingFacilityById(parkingFacilityId).get());
    }

    @GetMapping(value = "/city/{id}", produces = "application/json")
    public ResponseEntity<List<ParkingFacilityEntity>> getAlParkingFacilitiesByCityId(@PathVariable("id") Long cityId){
        return ResponseEntity.ok(parkingFacilityService.getAlParkingFacilitiesByCityId(cityId));
    }

    @PostMapping(value = "/car-park", consumes = "application/json")
    public ResponseEntity<CarParkEntity> addCarParkingFacilityForSpecificCity(@RequestBody CarParkingFacilityCityDTO parkingFacilityCity){
        return new ResponseEntity<>(parkingFacilityService.addCarParkingFacilityForSpecificCity(parkingFacilityCity), HttpStatus.CREATED);
    }

    @PostMapping(value = "/bike-rack", consumes = "application/json")
    public ResponseEntity<BikeRackEntity> addBikeRackFacilityForSpecificCity(@RequestBody BikeRackFacilityCityDTO bikeRackFacilityCityDTO){
        return new ResponseEntity<>(parkingFacilityService.addBikeRackFacilityForSpecificCity(bikeRackFacilityCityDTO), HttpStatus.CREATED);
    }
}
