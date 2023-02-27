package com.dronesservice.drones.service.data.model;

import com.dronesservice.drones.service.data.model.enums.DroneState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="Drone_load")
@NoArgsConstructor
@AllArgsConstructor
public @Data class DroneLoad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;
	
	@Column(name="drone_id")
	private String droneId;
	
	@Column(name="drone_state")
	@Enumerated(value = EnumType.STRING)
	private DroneState state;
	
	@Column(name="medications")
	@OneToMany
	private List<Medication> medications;
	
	@Column(name="loaded_number")
	private Integer quantity;
	
	@Column(name="loaded_wight")
	private Double loadedWight;

}
