package com.example.parkingapp.controllers;

import com.example.parkingapp.model.CityEntity;
import com.example.parkingapp.model.dtos.CityDTO;
import com.example.parkingapp.services.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/city", produces="application/json")
@RequiredArgsConstructor
@Slf4j
public class CityController {

    private final CityService cityService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CityEntity> getCityById(@PathVariable("id") Long cityId){
        return ResponseEntity.ok(cityService.getCityById(cityId).get());
    }

    @GetMapping(value = "/code/{code}", produces = "application/json")
    public ResponseEntity<CityEntity> getCityByCode(@PathVariable("code") String cityCode){
        return ResponseEntity.ok(cityService.getCityByCode(cityCode).get());
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CityEntity>> getAllCities(){
        return ResponseEntity.ok(cityService.getCities());
    }

    @PostMapping(consumes="application/json")
    public ResponseEntity<CityDTO> saveCity(@RequestBody CityDTO city) {
        return new ResponseEntity<>(cityService.saveCity(city), HttpStatus.CREATED);
    }
}
