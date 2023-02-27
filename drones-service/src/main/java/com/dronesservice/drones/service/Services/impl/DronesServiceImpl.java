package com.dronesservice.drones.service.Services.impl;

import com.dronesservice.drones.service.Exceptions.DroneNotAbleToLoadException;
import com.dronesservice.drones.service.Exceptions.DroneNotFoundException;
import com.dronesservice.drones.service.Exceptions.OverSizeLoadException;
import com.dronesservice.drones.service.Services.DronesService;
import com.dronesservice.drones.service.Services.MedicationService;
import com.dronesservice.drones.service.constants.ErrorMessages;
import com.dronesservice.drones.service.data.model.Drone;
import com.dronesservice.drones.service.data.model.DroneLoad;
import com.dronesservice.drones.service.data.model.Medication;
import com.dronesservice.drones.service.data.model.enums.DroneState;
import com.dronesservice.drones.service.dtos.*;
import com.dronesservice.drones.service.mappers.DroneLoadMapper;
import com.dronesservice.drones.service.mappers.DroneMapper;
import com.dronesservice.drones.service.repositories.DroneLoadRepository;
import com.dronesservice.drones.service.repositories.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DronesServiceImpl implements DronesService {

    private DroneMapper droneMapper;

    private DroneRepository droneRepository;

    private MedicationService medicationService;

    private DroneLoadRepository droneLoadRepository;
    private DroneLoadMapper droneLoadMapper;

    @Value("${battery.chargePercentage}")
    private Integer chargePercentage;
    @Value("${battery.maxPercentage}")
    private Integer maxPercentage;

    private final int BATTERY_MINIMUM_LIMIT = 25;

    @Autowired
    public DronesServiceImpl(DroneMapper droneMapper, DroneRepository droneRepository, MedicationService medicationService, DroneLoadRepository droneLoadRepository, DroneLoadMapper droneLoadMapper) {
        this.droneMapper = droneMapper;
        this.droneRepository = droneRepository;
        this.medicationService = medicationService;
        this.droneLoadRepository = droneLoadRepository;
        this.droneLoadMapper = droneLoadMapper;
    }



    @Override
    public DroneDTO registerDrone(DroneDTO droneDTO) {

        Drone readyForRegister = droneMapper.droneDtoToDrone(droneDTO);

        Drone registeredDrone = droneRepository.save(readyForRegister);

        return droneMapper.droneToDroneDto(registeredDrone);
    }

    @Override
    public LoadedDroneDTO loadDroneWithMedications(LoadDroneDTO loadDroneDTO) {

        Optional<Drone> readyForLoad = droneRepository.findById(loadDroneDTO.getSerialNumber());

        if(readyForLoad.isPresent()){

           List<Medication> medications =  medicationService.insertIntoDatabase(loadDroneDTO.getMedications());

            DroneLoad readyToSave = prepareDroneLoad(readyForLoad.get(),medications);
            DroneLoad savedLoadDrone = droneLoadRepository.save(readyToSave);
            LoadedDroneDTO droneDTO = droneLoadMapper.droneLoadToLoadedDroneDto(savedLoadDrone);

            return droneDTO;
        }
        throw new DroneNotFoundException(ErrorMessages.DRONE_NOT_FOUND_MESSAGE);
    }
    private DroneLoad prepareDroneLoad(Drone drone, List<Medication> medications ){



        if(drone.getBatterCapacity()<BATTERY_MINIMUM_LIMIT || drone.getDroneState()!= DroneState.IDLE)
            throw new DroneNotAbleToLoadException(ErrorMessages.DRONE_NOT_IN_IDEAL_STATE_MESSAGE);

        Double medicationWight = medications.stream()
                .map(Medication::getWeight).collect(Collectors.toList())
                .stream().collect(Collectors.summingDouble(Double::doubleValue));

        if(medicationWight > drone.getMaxWeight())
            throw new OverSizeLoadException(ErrorMessages.DRONE_OVER_SIZE_MESSAGE);

        drone.setDroneState(DroneState.LOADING);
        drone.setWeightLoaded(medicationWight);
        droneRepository.save(drone);
        DroneLoad droneLoad = droneLoadMapper.ToDroneLoad(drone,medications);

        droneLoad.setLoadedWight(medicationWight);

        return droneLoad;
    }
    @Override
    public LoadedDroneDTO getDrone(String serialNumber) {

        Optional<DroneLoad> drone = droneLoadRepository.findByDroneId(serialNumber);
        if(drone.isPresent())
        {
            LoadedDroneDTO targetLoadedDrone = droneLoadMapper.droneLoadToLoadedDroneDto(drone.get());
            
            return targetLoadedDrone;
        }
        throw new DroneNotFoundException(ErrorMessages.DRONE_NOT_FOUND_MESSAGE);
    }

    @Override
    public AvailableDronesDTO getAllAvailableDrones() {

        List<Drone> availableDrones = droneRepository.findAll();
        if(availableDrones.isEmpty())
            throw new DroneNotFoundException(ErrorMessages.NO_DRONES_FOUND_MESSAGE);
            List<DroneDTO> availableDronesDto = availableDrones.stream().filter(x->x.getDroneState()==DroneState.IDLE)
                    .map(droneMapper::droneToDroneDto).collect(Collectors.toList());
            AvailableDronesDTO allAvailable = new AvailableDronesDTO(availableDronesDto.size(),availableDronesDto);
        return allAvailable;
    }

    @Override
    public DroneBatteryDTO getDroneBatteryLevel(String serialNumber) {
        Optional<Drone> drone = droneRepository.findById(serialNumber);
        if(!drone.isPresent())
        {
            throw new DroneNotFoundException(ErrorMessages.DRONE_NOT_FOUND_MESSAGE);
        }
        DroneBatteryDTO droneBatteryDTO = droneMapper.droneToDroneBatteryDTO(drone.get());
        return droneBatteryDTO;
    }

    @Override
    public AuditDTO getDroneBattryAudit() {
        List<Drone> drones = droneRepository.findAll();

        List<DroneBatteryDTO> droneBatteryDTOList = drones.stream().map(droneMapper::droneToDroneBatteryDTO).collect(Collectors.toList());

        return new AuditDTO(droneBatteryDTOList);
    }

    @Override
    public String moveState() {
        List<Drone> drones = droneRepository.findAll();
        drones.stream().forEach( drone-> {
            if (drone.getDroneState() == DroneState.IDLE) {
                Integer batteryLevel = drone.getBatterCapacity() + chargePercentage;
                batteryLevel = batteryLevel > maxPercentage ? maxPercentage : batteryLevel;
                drone.setBatterCapacity(batteryLevel);
            }
            else{
                switch (drone.getDroneState())
                {
                   case  LOADING: {
                    drone.setDroneState(DroneState.LOADED);
                    break;}
                    case LOADED: {
                        drone.setDroneState(DroneState.DELIVERING);
                        break;}
                    case DELIVERING: {
                        drone.setDroneState(DroneState.DELIVERED);
                        break;}

                    case DELIVERED: {
                        drone.setDroneState(DroneState.RETURNING);
                        break;}
                    case RETURNING: {
                        drone.setDroneState(DroneState.IDLE);
                }
            }
        }

        }
        );
        droneRepository.saveAll(drones);
        return String.valueOf("state has been changed");
    }
}
