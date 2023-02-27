package com.monitoringservice.monitoringservice.apis;

import com.monitoringservice.monitoringservice.dtos.AuditLogsDTO;
import com.monitoringservice.monitoringservice.services.AuditBatteryHealthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/logs")
@Tag(name = "audit", description = "Logs API")
@CrossOrigin
public class AuditController {
    private AuditBatteryHealthService auditBatteryHealthService;

    public AuditController(AuditBatteryHealthService auditBatteryHealthService) {
        this.auditBatteryHealthService = auditBatteryHealthService;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<AuditLogsDTO> getDronesAndMedications(){

        return ResponseEntity.status(HttpStatus.OK).body( auditBatteryHealthService.getAllActivities());
    }
}
