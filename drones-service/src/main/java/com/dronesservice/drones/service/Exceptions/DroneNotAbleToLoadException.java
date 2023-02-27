package com.dronesservice.drones.service.Exceptions;

public class DroneNotAbleToLoadException extends RuntimeException {
    public DroneNotAbleToLoadException(String message) {
        super(message);
    }
}
