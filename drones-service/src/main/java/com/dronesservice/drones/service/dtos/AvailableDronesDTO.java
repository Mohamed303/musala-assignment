package com.dronesservice.drones.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDronesDTO {


	private int total;
	private List<DroneDTO> drones;

}
