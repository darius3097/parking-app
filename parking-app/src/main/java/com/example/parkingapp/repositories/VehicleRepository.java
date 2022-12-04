package com.example.parkingapp.repositories;

import com.example.parkingapp.model.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    @Query("SELECT v from VehicleEntity v WHERE v.id=:vehicleId AND v.parkingFacility.city.code=:cityCode")
    Optional<VehicleEntity> findByVehicleIdAndCityCode(Long vehicleId, String cityCode);

    @Query("SELECT v from VehicleEntity v WHERE v.id=:vehicleId AND v.parkingFacility.id=:parkingFacilityId")
    Optional<VehicleEntity> findByVehicleIdAndParkingFacilityId(Long vehicleId, Long parkingFacilityId);
}
