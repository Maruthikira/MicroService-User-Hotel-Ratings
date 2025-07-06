package com.hotel.HotelService.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceNotFound) {
        super(resourceNotFound);
    }
    public ResourceNotFoundException() {
        super("Resource Not Found exception");
    }
}
