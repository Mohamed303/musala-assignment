package com.dronesservice.drones.service.mappers;

import com.dronesservice.drones.service.data.model.Drone;
import com.dronesservice.drones.service.dtos.DroneBatteryDTO;
import com.dronesservice.drones.service.dtos.DroneDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;


@Mapper(componentModel = "spring")
@Service
public interface DroneMapper {

//    @Mapping(target = "serialNumber",source = "drone.serialNumber")
//    @Mapping(target = "droneModel",source = "droneModel")
//    @Mapping(target = "maxWeight",source = "maxWeight")
//    @Mapping(target = "batteryLife",source = "batteryLife")
//    @Mapping(target = "droneState",source = "droneState")
//    @Mapping(target = "weightLoaded",source = "weightLoaded")
    Drone droneDtoToDrone(DroneDTO droneDTO);
    DroneDTO droneToDroneDto(Drone drone);
    @Mapping(target = "droneState",source = "drone.droneState")
    DroneBatteryDTO droneToDroneBatteryDTO(Drone drone);
}
