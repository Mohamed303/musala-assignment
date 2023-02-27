package com.monitoringservice.monitoringservice.services.impl;

import com.monitoringservice.monitoringservice.Mapper;
import com.monitoringservice.monitoringservice.clients.DronesServiceClient;
import com.monitoringservice.monitoringservice.dtos.AuditDTO;
import com.monitoringservice.monitoringservice.dtos.AuditLogsDTO;
import com.monitoringservice.monitoringservice.model.DroneLog;
import com.monitoringservice.monitoringservice.repo.DroneBatteryRepository;
import com.monitoringservice.monitoringservice.services.AuditBatteryHealthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditBatteryHealthServiceImpl implements AuditBatteryHealthService {
    private DronesServiceClient dronesServiceClient;
    private DroneBatteryRepository droneBatteryRepository;

    private Mapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger("default-logger");
    public AuditBatteryHealthServiceImpl(DronesServiceClient dronesServiceClient, DroneBatteryRepository droneBatteryRepository,Mapper mapper) {
        this.dronesServiceClient = dronesServiceClient;
        this.droneBatteryRepository = droneBatteryRepository;
        this.mapper = mapper;
    }

    @Override
    public void changeState() {
        dronesServiceClient.moveDronesState();
    }

    @Override
    public AuditLogsDTO getAllActivities() {
       List<DroneLog> logsList = droneBatteryRepository.findAll();
        AuditLogsDTO auditLogsDTO = new AuditLogsDTO (LocalDateTime.now(),logsList);
        return auditLogsDTO;
    }

    @Override
    public void fetchTheLogs() {
        AuditDTO auditDTO = dronesServiceClient.getDroneAudit().getBody();
        if(auditDTO.getDrones().isEmpty()){
            LOGGER.info("------->fetch an empty log<--------");
        }
        else {
            auditDTO.getDrones().forEach(x->{
                droneBatteryRepository.save(mapper.ToDroneLog(x));
            });
        }

    }
    @Scheduled(fixedDelayString = "${drone.logPeriod}")
    public void fetchLogsSchedule(){
        fetchTheLogs();
        LOGGER.info("------->fetching log schedule<--------");
    }
    @Scheduled(fixedDelayString = "${drone.statePeriod}")
    public void changeStateSchedule(){
        changeState();
        LOGGER.info("------->change state schedule<--------");
    }
}
