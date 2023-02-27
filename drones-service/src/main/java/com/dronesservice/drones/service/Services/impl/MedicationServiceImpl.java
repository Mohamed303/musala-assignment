package com.dronesservice.drones.service.Services.impl;

import com.dronesservice.drones.service.Services.MedicationService;
import com.dronesservice.drones.service.data.model.Medication;
import com.dronesservice.drones.service.dtos.MedicationDTO;
import com.dronesservice.drones.service.mappers.MedicationMapper;
import com.dronesservice.drones.service.repositories.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationServiceImpl implements MedicationService {

    private MedicationMapper medicationMapper;

    private MedicationRepository medicationRepository;

    @Autowired
    public MedicationServiceImpl(MedicationMapper medicationMapper, MedicationRepository medicationRepository) {
        this.medicationMapper = medicationMapper;
        this.medicationRepository = medicationRepository;
    }

    @Override
    public List<Medication> insertIntoDatabase(List<MedicationDTO> medications) {

        List<Medication> medicationsReadyForStore = medications.stream()
                .map(medicationMapper::medicationDtoToMedication).collect(Collectors.toList());

        List<Medication> storedMedication = medicationsReadyForStore.stream().map(medicationRepository::save).collect(Collectors.toList());

        return storedMedication;
    }
}
