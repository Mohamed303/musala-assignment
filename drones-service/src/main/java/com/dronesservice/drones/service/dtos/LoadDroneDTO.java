package com.dronesservice.drones.service.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


public @Data class LoadDroneDTO {
	
	@NotNull(message = "Drone serial number shouldn't be empty")
	@Size(min=3,max=100,message="Serial number should be more tha 2 less than 100 characters")
	@JsonProperty(required = true)
	@NotEmpty
	@NotBlank
	@Schema(name = "serialNumber", description = "drone serial number", requiredMode = Schema.RequiredMode.REQUIRED
			, example = "1234")
	private String serialNumber;

	
	@JsonProperty(required = true)
	@NotEmpty(message="Medications required")
	private List<MedicationDTO> medications;
}
