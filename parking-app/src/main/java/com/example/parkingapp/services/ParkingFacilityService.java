package com.example.parkingapp.services;

import com.example.parkingapp.exceptions.ResourceAlreadyExists;
import com.example.parkingapp.exceptions.ResourceNotFoundException;
import com.example.parkingapp.model.*;
import com.example.parkingapp.model.dtos.BikeRackFacilityCityDTO;
import com.example.parkingapp.model.dtos.CarParkingFacilityCityDTO;
import com.example.parkingapp.repositories.BikeRackRepository;
import com.example.parkingapp.repositories.CarParkRepository;
import com.example.parkingapp.repositories.CityRepository;
import com.example.parkingapp.repositories.ParkingFacilityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParkingFacilityService {

    private final ParkingFacilityRepository parkingFacilityRepository;
    private final CityRepository cityRepository;
    private final CarParkRepository carParkRepository;
    private final BikeRackRepository bikeRackRepository;

    public Optional<ParkingFacilityEntity> getParkingFacilityById(Long id) {
        return Optional.ofNullable(parkingFacilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parking Facility with this id was not found")));
    }

    public List<ParkingFacilityEntity> getAlParkingFacilitiesByCityId(Long cityId) {
        Optional<CityEntity> city = cityRepository.findById(cityId);

        if(city.isEmpty()){
            throw new ResourceNotFoundException("City was not found");
        }

        return parkingFacilityRepository.findAllByCity(city.get());
    }

    @Transactional
    public CarParkEntity addCarParkingFacilityForSpecificCity(CarParkingFacilityCityDTO carParkingFacilityCity) {
        if(carParkingFacilityCity.getCarPark().getId() != null && carParkRepository.findById((carParkingFacilityCity.getCarPark().getId())).isPresent()){
            throw new ResourceAlreadyExists("Parking Facility already exists in DB");
        }

        Optional<CityEntity> city = cityRepository.findCityEntityByCode(carParkingFacilityCity.getCity().getCode());

        if(carParkingFacilityCity.getCity().getId() != null && (city.isPresent() || cityRepository.findById(carParkingFacilityCity.getCity().getId()).isPresent())){
            carParkingFacilityCity.getCarPark().setCity(city.get());
        }else {
            CityEntity citySaved = cityRepository.save(carParkingFacilityCity.getCity());
            carParkingFacilityCity.getCarPark().setCity(citySaved);
        }

        return carParkRepository.save(carParkingFacilityCity.getCarPark());
    }

    @Transactional
    public BikeRackEntity addBikeRackFacilityForSpecificCity(BikeRackFacilityCityDTO bikeRackFacilityCityDTO) {
        if(bikeRackFacilityCityDTO.getBikeRack().getId() != null && carParkRepository.findById((bikeRackFacilityCityDTO.getBikeRack().getId())).isPresent()){
            throw new ResourceAlreadyExists("Bike Rack Facility already exists in DB");
        }

        Optional<CityEntity> city = cityRepository.findCityEntityByCode(bikeRackFacilityCityDTO.getCity().getCode());

        if(bikeRackFacilityCityDTO.getCity().getId() != null && (city.isPresent() || cityRepository.findById(bikeRackFacilityCityDTO.getCity().getId()).isPresent())){
            bikeRackFacilityCityDTO.getBikeRack().setCity(city.get());
        }else {
            CityEntity citySaved = cityRepository.save(bikeRackFacilityCityDTO.getCity());
            bikeRackFacilityCityDTO.getBikeRack().setCity(citySaved);
        }

        return bikeRackRepository.save(bikeRackFacilityCityDTO.getBikeRack());
    }
}
