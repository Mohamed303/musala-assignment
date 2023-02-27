package com.dronesservice.drones.service.dtos;

import com.dronesservice.drones.service.data.model.enums.DroneModel;
import com.dronesservice.drones.service.data.model.enums.DroneState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DroneDTO {
	
@NotNull(message = "Drone serial number must not be empty")
@Size(min=3,max=100,message="Drone serial number must not be greater than {value} characters")
@JsonProperty(required = true)
@NotEmpty
@NotBlank
@Schema(name = "serialNumber", description = "serial number"
        , example = "e2123")
private String serialNumber;
	
	
@JsonProperty(required = true)
@NotNull(message="Drone model required")
@Schema(name = "droneModel", description = "drone model"
        , example = "Lightweight|Middleweight|Cruiserweight|Heavyweight")
private DroneModel droneModel;

@JsonProperty(required = true)
@DecimalMax(value = "500", message =" Drone cannot carry more than {value} grams")
@NotNull(message = "maxWeight must not be empty")
@Schema(name = "maxWeight", description = "max weight cloud drone curry"
        , example = "500")

private Double maxWeight;

@JsonProperty(required = true)
@NotNull(message="Battery life required")
@Schema(name = "batterCapacity", description = "battery life"
        , example = "25")
private Integer batterCapacity;

@JsonProperty(required = true)
@NotNull(message="droneState life required")
@Schema(name = "droneState", description = "drone status"
        , example = "IDLE|LOADING|LOADED|DELIVERING|DELIVERED|RETURNING")

private DroneState droneState;

@Schema(name = "weightLoaded", description = "the wight which drone is curry notice it's by default zero"
            , example = "0")
private Double weightLoaded; 
	

}
