package com.dronesservice.drones.service.Exceptions;

import com.dronesservice.drones.service.dtos.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class GloabalExceptionHandler {

    @ExceptionHandler({DroneNotAbleToLoadException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageDto handleHttpMessageNotReadable(DroneNotAbleToLoadException ex) {
       return new ErrorMessageDto(ex.getMessage());
    }


    @ExceptionHandler({DroneNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageDto handleHttpMessageNotReadable(DroneNotFoundException ex) {
        return new ErrorMessageDto(ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageDto handleHttpMessageNotReadable(MethodArgumentNotValidException ex) {
        return new ErrorMessageDto(ex.getAllErrors().get(0).getDefaultMessage());
    }


}
