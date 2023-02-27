package com.monitoringservice.monitoringservice;

import com.monitoringservice.monitoringservice.dtos.DroneBatteryDTO;
import com.monitoringservice.monitoringservice.model.DroneLog;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;


@org.mapstruct.Mapper(componentModel = "spring")
@Service
public interface Mapper {
    @Mapping(expression = "java(java.time.LocalDateTime.now())",target = "time")
    DroneLog ToDroneLog(DroneBatteryDTO droneBatteryDTO);

}
