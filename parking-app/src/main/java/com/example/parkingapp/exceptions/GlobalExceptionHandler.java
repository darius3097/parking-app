package com.example.parkingapp.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        return new ResponseEntity(resourceNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity handleResourceAlreadyExists(ResourceAlreadyExists resourceAlreadyExists){
        return new ResponseEntity(resourceAlreadyExists.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceDifferentException.class)
    public ResponseEntity handleResourceAlreadyExists(ResourceDifferentException resourceDifferentException){
        return new ResponseEntity(resourceDifferentException.getMessage(), HttpStatus.FORBIDDEN);
    }
}
