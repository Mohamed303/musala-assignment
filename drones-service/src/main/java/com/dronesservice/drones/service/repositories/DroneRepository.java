package com.dronesservice.drones.service.repositories;

import com.dronesservice.drones.service.data.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DroneRepository extends JpaRepository<Drone,String> {

}
