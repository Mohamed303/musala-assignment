package com.dronesservice.drones.service.Services;

import com.dronesservice.drones.service.data.model.Medication;
import com.dronesservice.drones.service.dtos.MedicationDTO;

import java.util.List;

public interface MedicationService {
   public List <Medication> insertIntoDatabase(List<MedicationDTO> medications);
}
