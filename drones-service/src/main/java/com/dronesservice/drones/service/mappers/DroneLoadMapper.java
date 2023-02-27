package com.dronesservice.drones.service.mappers;

import com.dronesservice.drones.service.data.model.Drone;
import com.dronesservice.drones.service.data.model.DroneLoad;
import com.dronesservice.drones.service.data.model.Medication;
import com.dronesservice.drones.service.dtos.LoadedDroneDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper(componentModel = "spring")
@Service
public interface DroneLoadMapper {
    @Mapping(source = "drone.droneState",target = "state")
    @Mapping(source = "drone.serialNumber",target = "droneId")
    @Mapping(expression = "java(medicationList)",target = "medications")
    @Mapping(expression = "java(medicationList.size())",target = "quantity")
    DroneLoad ToDroneLoad(Drone drone, List<Medication> medicationList);

    LoadedDroneDTO droneLoadToLoadedDroneDto(DroneLoad droneLoad);
}
