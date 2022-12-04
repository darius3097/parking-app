package com.example.parkingapp.repositories;

import com.example.parkingapp.model.BikeRackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRackRepository extends JpaRepository<BikeRackEntity, Long> {
}
