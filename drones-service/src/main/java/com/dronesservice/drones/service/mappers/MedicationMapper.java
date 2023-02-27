package com.dronesservice.drones.service.mappers;

import com.dronesservice.drones.service.data.model.Medication;
import com.dronesservice.drones.service.dtos.MedicationDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface MedicationMapper {
    Medication medicationDtoToMedication(MedicationDTO medicationDTO);
}
