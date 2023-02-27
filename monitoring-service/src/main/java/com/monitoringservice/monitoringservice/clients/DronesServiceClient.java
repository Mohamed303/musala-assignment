package com.monitoringservice.monitoringservice.clients;

import com.monitoringservice.monitoringservice.dtos.AuditDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name= "droneService")
public interface DronesServiceClient {
    @PostMapping("/api/drones/moveState")
    public ResponseEntity<String> moveDronesState();
    @GetMapping("/api/drones/audit")
    public ResponseEntity<AuditDTO> getDroneAudit();
}
