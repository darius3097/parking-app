package com.example.parkingapp.services;

import com.example.parkingapp.exceptions.ResourceDifferentException;
import com.example.parkingapp.exceptions.ResourceNotFoundException;
import com.example.parkingapp.model.CityEntity;
import com.example.parkingapp.model.ParkingFacilityEntity;
import com.example.parkingapp.model.VehicleEntity;
import com.example.parkingapp.model.dtos.CityDTO;
import com.example.parkingapp.repositories.CityRepository;
import com.example.parkingapp.repositories.ParkingFacilityRepository;
import com.example.parkingapp.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final CityRepository cityRepository;
    private final ParkingFacilityRepository parkingFacilityRepository;

    public Optional<VehicleEntity> getVehicleByCityCode(Long vehicleId, String cityCode) {
        return Optional.ofNullable(vehicleRepository.findByVehicleIdAndCityCode(vehicleId, cityCode)
                .orElseThrow(()-> new ResourceNotFoundException("Vehicle was not found in this city")));
    }

    @Transactional
    public VehicleEntity createVehicleForASpecificCity(CityDTO cityDTO) {
        Optional<CityEntity> city = cityRepository.findCityEntityByCode(cityDTO.getCode());

        if(city.isPresent()){
            return vehicleRepository.save(VehicleEntity
                    .builder()
                            .isParked(false)
                            .city(city.get())
                    .build());
        } else {
            if(!StringUtils.hasLength(cityDTO.getName())){
                throw new ResourceNotFoundException("Please provide a city name because there are no city with this code");
            }

            CityEntity citySaved = cityRepository.save(CityDTO.convertToEntity(cityDTO));
            return vehicleRepository.save(VehicleEntity
                    .builder()
                    .isParked(false)
                    .city(citySaved)
                    .build());
        }
    }

    @Transactional
    public void parkVehicleInParkingFacility(Long vehicleId, Long parkingFacilityId) {
        Optional<VehicleEntity> vehicle = vehicleRepository.findById(vehicleId);
        if(vehicle.isEmpty() || !StringUtils.hasLength(vehicle.get().getType()) || !vehicle.get().getType().equals("Car")) {
            throw new ResourceNotFoundException("This vehicle doesn't exist in DB");
        }

        Optional<ParkingFacilityEntity> parkingFacility = parkingFacilityRepository.findById(parkingFacilityId);

        if(parkingFacility.isEmpty() || !parkingFacility.get().getType().equals("CarPark")){
            throw new ResourceNotFoundException("This parking facility doesn't exists in db");
        }
        if(!vehicle.get().getCity().getCode().equals( parkingFacility.get().getCity().getCode())){
            throw new ResourceDifferentException("The vehicle must come from the same city as parking facility");
        }

        vehicle.get().setParkingFacility(parkingFacility.get());
        vehicle.get().setIsParked(true);

        vehicleRepository.save(vehicle.get());
    }
    @Transactional
    public void unparkVehicleFromParkingFacility(Long vehicleId, Long parkingFacilityId) {
        Optional<VehicleEntity> vehicle = vehicleRepository.findByVehicleIdAndParkingFacilityId(vehicleId, parkingFacilityId);

        if(vehicle.isEmpty()){
            throw new ResourceNotFoundException("Vehicle was not found in this parking facility ");
        }

        vehicle.get().setIsParked(false);
        vehicle.get().setParkingFacility(null);
        vehicleRepository.save(vehicle.get());
    }
}
