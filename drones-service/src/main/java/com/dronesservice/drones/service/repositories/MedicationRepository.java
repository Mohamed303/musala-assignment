package com.dronesservice.drones.service.repositories;


import com.dronesservice.drones.service.data.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication,Long> {

}
