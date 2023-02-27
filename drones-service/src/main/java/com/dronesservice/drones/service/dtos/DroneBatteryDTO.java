package com.dronesservice.drones.service.dtos;

import lombok.Data;

@Data
public class DroneBatteryDTO {
	
	private String serialNumber;
	private String droneState;
	private int batterCapacity;

}
