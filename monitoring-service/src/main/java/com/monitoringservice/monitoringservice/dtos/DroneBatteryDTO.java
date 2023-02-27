package com.monitoringservice.monitoringservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroneBatteryDTO {
	
	private String serialNumber;
	private String droneState;
	private int batterCapacity;

}
