package com.bergman.service.exceptions;

public class FlightNotFoundException extends RuntimeException {

    public FlightNotFoundException(Long id) {
        super("" + id);
    }
}
