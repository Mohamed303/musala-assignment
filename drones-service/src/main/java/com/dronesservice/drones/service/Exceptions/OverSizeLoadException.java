package com.dronesservice.drones.service.Exceptions;

public class OverSizeLoadException extends RuntimeException {
    public OverSizeLoadException(String message) {
        super(message);
    }
}
