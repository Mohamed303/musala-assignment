package com.dronesservice.drones.service.Services;

import com.dronesservice.drones.service.dtos.*;

import java.util.List;

public interface DronesService {
    public DroneDTO registerDrone(DroneDTO droneDTO);

    public LoadedDroneDTO loadDroneWithMedications(LoadDroneDTO loadDroneDTO);

   public LoadedDroneDTO getDrone(String serialNumber);

    public AvailableDronesDTO getAllAvailableDrones();

    public DroneBatteryDTO getDroneBatteryLevel(String serialNumber);

    AuditDTO getDroneBattryAudit();

    String moveState();
}
