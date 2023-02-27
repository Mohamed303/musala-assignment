package com.monitoringservice.monitoringservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
// put it into dto just in case if we want add new  parameters feature
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditDTO {
	
	private List<DroneBatteryDTO> drones;

}
