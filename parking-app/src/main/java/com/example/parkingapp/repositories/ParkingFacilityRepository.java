package com.example.parkingapp.repositories;

import com.example.parkingapp.model.CityEntity;
import com.example.parkingapp.model.ParkingFacilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingFacilityRepository extends JpaRepository<ParkingFacilityEntity, Long> {
    List<ParkingFacilityEntity> findAllByCity(CityEntity city);
}
