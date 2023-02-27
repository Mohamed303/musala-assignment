package com.monitoringservice.monitoringservice.dtos;

import com.monitoringservice.monitoringservice.model.DroneLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogsDTO {
    private LocalDateTime time;
    private List<DroneLog> logs;
}
