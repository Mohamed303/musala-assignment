package com.dronesservice.drones.service.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;

@Data
public class MedicationDTO {

@JsonIgnore
private Long id;
	
@JsonProperty(required = true)
@Schema(name = "name", description = "medication name", requiredMode = Schema.RequiredMode.REQUIRED
		, example = "COVID-19 vaccine")
private String name;
	
@JsonProperty(required = true)
@Max(value=500, message="Weight too heavy for drone")
@Schema(name = "weight", description = "medication wight", requiredMode = Schema.RequiredMode.REQUIRED
		, example = "2.4")
private Double weight;
	
	
@JsonProperty(required = true)
@Schema(name = "code", description = "medication code", requiredMode = Schema.RequiredMode.REQUIRED
		, example = "242-123")
private String code;

@JsonProperty(required = true)
@Schema(name = "imageUrl", description = "medication image url", requiredMode = Schema.RequiredMode.REQUIRED
		, example = "https://www.google.com/search?q=image+for+medication&sxsrf=AJOqlzWSFcGRoFe22gcbtoxYHjm-ejmsSg:1677281258070")
private String imageUrl;

}
