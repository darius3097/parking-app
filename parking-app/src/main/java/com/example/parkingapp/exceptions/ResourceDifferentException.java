package com.example.parkingapp.exceptions;

public class ResourceDifferentException extends RuntimeException{
    public ResourceDifferentException() {
    }

    public ResourceDifferentException(String message) {
        super(message);
    }
}
