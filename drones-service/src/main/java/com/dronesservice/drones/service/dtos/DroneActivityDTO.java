package com.dronesservice.drones.service.dtos;

import lombok.Data;

import java.util.List;

@Data
public class DroneActivityDTO {

	private int total;
	private List<LoadDroneDTO> activities;
}
