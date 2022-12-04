package com.example.parkingapp.controllers;

import com.example.parkingapp.model.VehicleEntity;
import com.example.parkingapp.model.dtos.CityDTO;
import com.example.parkingapp.services.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vehicle", produces="application/json")
@RequiredArgsConstructor
@Slf4j
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping(value = "/{id}/city/{code}", produces = "application/json")
    public ResponseEntity<VehicleEntity> getCityById(@PathVariable("id") Long vehicleId, @PathVariable("code") String cityCode){
        return ResponseEntity.ok(vehicleService.getVehicleByCityCode(vehicleId, cityCode).get());
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<VehicleEntity> createVehicleForASpecificCity(@RequestBody CityDTO cityDTO) {
        return new ResponseEntity<>(vehicleService.createVehicleForASpecificCity(cityDTO), HttpStatus.CREATED);
    }

    @PostMapping(value ="/park/{id}/parking-facility/{pkid}" ,consumes = "application/json")
    public ResponseEntity<Void> parkVehicleInParkingFacility(@PathVariable("id") Long vehicleId,
                                                                      @PathVariable("pkid") Long parkingFacilityId ) {
        vehicleService.parkVehicleInParkingFacility(vehicleId, parkingFacilityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value ="/unpark/{id}/parking-facility/{pkid}" ,consumes = "application/json")
    public ResponseEntity<Void> unparkVehicleFromParkingFacility(@PathVariable("id") Long vehicleId,
                                                             @PathVariable("pkid") Long parkingFacilityId ) {
        vehicleService.unparkVehicleFromParkingFacility(vehicleId, parkingFacilityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
