package com.example.parkingapp.repositories;

import com.example.parkingapp.model.CarParkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarParkRepository extends JpaRepository<CarParkEntity, Long> {
}
