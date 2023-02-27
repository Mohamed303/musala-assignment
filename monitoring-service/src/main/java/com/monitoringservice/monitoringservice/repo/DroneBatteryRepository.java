package com.monitoringservice.monitoringservice.repo;

import com.monitoringservice.monitoringservice.model.DroneLog;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DroneBatteryRepository extends MongoRepository<DroneLog,String> {

}
