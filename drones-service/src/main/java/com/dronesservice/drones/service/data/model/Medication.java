package com.dronesservice.drones.service.data.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="medications")
public @Data class Medication {
	
	@Id
	@GeneratedValue(strategy =GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "medication_name")
	@NotNull
	private String name;
	
	@Column(name ="weight")
	private Double weight;
	
	@Column(name="medication_code")
	private String code;

	@Column(name = "image_url")
	private String imageUrl;

}
