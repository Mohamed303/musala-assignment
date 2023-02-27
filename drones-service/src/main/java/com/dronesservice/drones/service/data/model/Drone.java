package com.dronesservice.drones.service.data.model;

import com.dronesservice.drones.service.data.model.enums.DroneModel;
import com.dronesservice.drones.service.data.model.enums.DroneState;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;


@Entity
@Table(name ="drones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Drone {
	
	@Id
	@Column(name="serial_number", length = 100)
	private String serialNumber;
	
	@Column(name="model")
	@Enumerated(value = EnumType.STRING)
	private DroneModel droneModel;
	
	@Column(name="weight_max")
	@DecimalMax(value = "500", message ="maximum Drone carry is {value}")
	private Double maxWeight;
	
	@Column(name="battery_capacity")
	@Max(value=100,message="maximum Battery life 100%")
	private Integer batterCapacity;
	
	@Column(name = "drone_state")
	@Enumerated(value = EnumType.STRING)
	private DroneState droneState;
	
	@Column(name = "weight_loaded")
	private Double weightLoaded; //to track the weight of the drone during loading time
	
	
	

}
