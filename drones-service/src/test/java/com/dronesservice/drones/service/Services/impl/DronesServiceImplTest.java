package com.dronesservice.drones.service.Services.impl;

import com.dronesservice.drones.service.Exceptions.DroneNotFoundException;
import com.dronesservice.drones.service.data.model.Drone;
import com.dronesservice.drones.service.data.model.DroneLoad;
import com.dronesservice.drones.service.data.model.enums.DroneModel;
import com.dronesservice.drones.service.data.model.enums.DroneState;
import com.dronesservice.drones.service.dtos.AvailableDronesDTO;
import com.dronesservice.drones.service.dtos.DroneDTO;
import com.dronesservice.drones.service.dtos.LoadedDroneDTO;
import com.dronesservice.drones.service.mappers.DroneLoadMapper;
import com.dronesservice.drones.service.mappers.DroneMapper;
import com.dronesservice.drones.service.repositories.DroneLoadRepository;
import com.dronesservice.drones.service.repositories.DroneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DronesServiceImplTest {
    @Mock
    private DroneMapper droneMapper;
    @Mock
    private DroneLoadMapper droneLoadMapper;

    @Mock
    private DroneRepository droneRepository;

    @InjectMocks
    private DronesServiceImpl droneService;
    @Mock
    private DroneLoadRepository droneLoadRepository;

    @Test
    void registerDrone() {
        DroneDTO droneDTO = new DroneDTO();
        droneDTO.setSerialNumber("123");
        droneDTO.setDroneModel(DroneModel.Lightweight);
        droneDTO.setDroneState(DroneState.IDLE);
        droneDTO.setMaxWeight(22.22);

        // Create a mock Drone object to return from the repository
        Drone drone = new Drone();

        drone.setSerialNumber("123");
        drone.setDroneModel(DroneModel.Lightweight);
        drone.setDroneState(DroneState.IDLE);
        drone.setMaxWeight(22.22);
        when(droneMapper.droneDtoToDrone(droneDTO)).thenReturn(drone);
        when(droneMapper.droneToDroneDto(drone)).thenReturn(droneDTO);

        when(droneRepository.save(drone)).thenReturn(drone);

        DroneDTO result = droneService.registerDrone(droneDTO);


        Assertions.assertEquals(droneDTO, result);
    }

    @Test
    void getDrone() {
        String serialNumber = "1234";
        DroneLoad droneLoad = new DroneLoad();
        LoadedDroneDTO expectedLoadedDrone = new LoadedDroneDTO();
        when(droneLoadRepository.findByDroneId(serialNumber)).thenReturn(Optional.of(droneLoad));
        when(droneLoadMapper.droneLoadToLoadedDroneDto(droneLoad)).thenReturn(expectedLoadedDrone);

        LoadedDroneDTO result = droneService.getDrone(serialNumber);

        assertEquals(expectedLoadedDrone, result);
    }

    @Test
    public void testGetDrone_WhenDroneNotFound() {
        String serialNumber = "5678";
        when(droneLoadRepository.findByDroneId(serialNumber)).thenReturn(Optional.empty());

        assertThrows(DroneNotFoundException.class, () -> droneService.getDrone(serialNumber));
    }
    @Test
    void getAllAvailableDrones() {
        List<Drone> drones = new ArrayList<>();
        Drone drone = new Drone();

        drone.setSerialNumber("123");
        drone.setDroneModel(DroneModel.Lightweight);
        drone.setDroneState(DroneState.IDLE);
        drone.setMaxWeight(22.22);
        drones.add(drone);

        when(droneRepository.findAll()).thenReturn(drones);
        DroneDTO droneDTO = new DroneDTO();
        droneDTO.setSerialNumber("123");
        droneDTO.setDroneModel(DroneModel.Lightweight);
        droneDTO.setDroneState(DroneState.IDLE);
        droneDTO.setMaxWeight(22.22);
        when(droneMapper.droneToDroneDto(drone)).thenReturn(droneDTO);
        // Call the method under test
        AvailableDronesDTO result = droneService.getAllAvailableDrones();

        // Assert the results
        assertEquals(1, result.getDrones().size());

        assertTrue(result.getDrones().stream().
                allMatch(d ->
                        d.getDroneState() == DroneState.IDLE));
    }

}