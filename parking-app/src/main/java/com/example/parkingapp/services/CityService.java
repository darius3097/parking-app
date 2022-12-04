package com.example.parkingapp.services;

import com.example.parkingapp.exceptions.ResourceAlreadyExists;
import com.example.parkingapp.model.CityEntity;
import com.example.parkingapp.exceptions.ResourceNotFoundException;
import com.example.parkingapp.model.dtos.CityDTO;
import com.example.parkingapp.repositories.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityService {

    private final CityRepository cityRepository;

    public Optional<CityEntity> getCityById(Long id) {
        return Optional.ofNullable(cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City with this id was not found")));
    }

    public Optional<CityEntity> getCityByCode(String code) {
        return Optional.ofNullable(cityRepository.findCityEntityByCode(code)
                .orElseThrow(()-> new ResourceNotFoundException("City with this code was not found")));
    }

    public List<CityEntity> getCities() {
        return cityRepository.findAll();
    }

    @Transactional
    public CityDTO saveCity(CityDTO city) {
        if((city.getId() != null && (cityRepository.findById(city.getId()).isPresent()) ||
                cityRepository.findCityEntityByCode(city.getCode()).isPresent())){
            throw new ResourceAlreadyExists("City already exists in DB");
        }

       CityEntity citySaved = cityRepository.save(CityDTO.convertToEntity(city));

        return CityDTO.convertToDTO(citySaved);
    }
}
