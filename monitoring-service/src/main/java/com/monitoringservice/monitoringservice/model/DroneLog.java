package com.monitoringservice.monitoringservice.model;

import com.monitoringservice.monitoringservice.dtos.DroneBatteryDTO;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@ToString

@Document(collection = "droneLog")
public class DroneLog {

    @Id
    private String serialNumber;
    private String droneState;
    private int batterCapacity;
    private LocalDateTime time;

}
