package com.dronesservice.drones.service.apis;

import com.dronesservice.drones.service.Services.DronesService;
import com.dronesservice.drones.service.dtos.*;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/drones")
@Tag(name = "Drones", description = "Drones API")
@Validated
@CrossOrigin
public class DronesController {

    private DronesService dronesService;

    DronesController(DronesService dronesService){
        this.dronesService = dronesService;
    }


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register a new Drone")
    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = DroneDTO.class)))
    @ApiResponse(responseCode = "400", description = "bad request")
    @ApiResponse(responseCode = "409", description = "conflict")
    ResponseEntity<DroneDTO> registerDrone(@RequestBody @Valid DroneDTO droneDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(dronesService.registerDrone(droneDTO));

    }

    @PostMapping("/load/{serialNumber}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Load the drone with medications")
    @ApiResponse(responseCode = "200", description = "Load the drone with medications", content = @Content(schema = @Schema(implementation = LoadDroneDTO.class)))
    @ApiResponse(responseCode = "400", description = "bad request")
    @ApiResponse(responseCode = "404", description = "not found")
    ResponseEntity<LoadedDroneDTO> loadDroneWithMedications(@Validated @RequestBody LoadDroneDTO loadDroneDTO)
   {
        return new ResponseEntity(dronesService.loadDroneWithMedications(loadDroneDTO), HttpStatus.OK);
    }

    @GetMapping("/load/{serialNumber}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get medications by drone serial number")
    @ApiResponse(responseCode = "200", description = "", content = @Content(schema = @Schema(implementation = LoadDroneDTO.class)))
    @ApiResponse(responseCode = "400", description = "bad request")
    @ApiResponse(responseCode = "404", description = "not found")
    ResponseEntity<LoadedDroneDTO> getDronesAndMedications(@PathVariable("serialNumber") @NotNull(message = "serial number required")
                                                         @NotBlank(message = "serial number required") String serialNumber){

        return ResponseEntity.status(HttpStatus.OK).body( dronesService.getDrone(serialNumber));
    }


    @GetMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all available drones for loading")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = AvailableDronesDTO.class)))
    @ApiResponse(responseCode = "400", description = "bad request")
    @ApiResponse(responseCode = "404", description = "not found")
    ResponseEntity<AvailableDronesDTO> getAvailableDroneForLoading(){
        return new ResponseEntity(this.dronesService.getAllAvailableDrones(), HttpStatus.OK);
    }

    @GetMapping("/batteryLevel/{serialNumber}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get drone battery level")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DroneBatteryDTO.class)))
    @ApiResponse(responseCode = "400", description = "bad request")
    @ApiResponse(responseCode = "404", description = "not found")
    ResponseEntity<DroneBatteryDTO> checkDroneBatteryLevel(@PathVariable("serialNumber") @NotNull(message = "serial number required")
                                                           @NotBlank(message = "serial number required") String serialNumber){
        return new ResponseEntity(this.dronesService.getDroneBatteryLevel(serialNumber), HttpStatus.OK);
    }
    @Hidden
    @GetMapping("/audit")
    public ResponseEntity<AuditDTO> getDroneAudit(){
        AuditDTO audit = dronesService.getDroneBattryAudit();
        return ResponseEntity.ok(audit);

    }
    @Hidden
    @PostMapping("/moveState")
    public ResponseEntity<String> moveDronesState(){
        String message = dronesService.moveState();
        return ResponseEntity.ok(message);

    }

}
