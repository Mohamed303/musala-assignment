package com.dronesservice.drones.service.dtos;

import com.dronesservice.drones.service.data.model.Medication;
import com.dronesservice.drones.service.data.model.enums.DroneState;
import lombok.Data;

import java.util.List;
@Data
public class LoadedDroneDTO {

    private String droneId;

    private DroneState state;

    private List<Medication> medications;

    private Integer quantity;

    private Double loadedWight;
}
