package com.dronesservice.drones.service.repositories;


import com.dronesservice.drones.service.data.model.DroneLoad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DroneLoadRepository extends JpaRepository<DroneLoad,Long> {
    public Optional<DroneLoad> findByDroneId(String droneId);

}
