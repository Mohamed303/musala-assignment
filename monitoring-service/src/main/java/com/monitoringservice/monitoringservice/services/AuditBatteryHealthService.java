package com.monitoringservice.monitoringservice.services;

import com.monitoringservice.monitoringservice.dtos.AuditDTO;
import com.monitoringservice.monitoringservice.dtos.AuditLogsDTO;

public interface AuditBatteryHealthService {
    public void changeState();
    AuditLogsDTO getAllActivities();
    void fetchTheLogs();
}
