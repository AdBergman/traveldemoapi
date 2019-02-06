package com.bergman.rest.controller;

import com.bergman.repository.entity.Flight;
import com.bergman.service.exceptions.FlightNotFoundException;
import com.bergman.service.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/flights")
    List<Flight> getAllFlights(){
        return flightService.getAllFlights();
    }

    @PostMapping("/flights")
    Flight newFlight(@RequestBody Flight newFlight) {
        return flightService.saveFlight(newFlight);
    }

    @GetMapping("/flights/{id}")
    Flight one(@PathVariable Long id) {
        try {
            return flightService.getFlight(id);
        } catch (FlightNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Flight Not Found.", e);
        }
    }

    @PutMapping("/flights/{id}")
    Flight replaceFlight(@RequestBody Flight newFlight, @PathVariable Long id) {
        try {
        return flightService.replaceFlight(id, newFlight);
        } catch (FlightNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Flight Not Found.", e);
        }
    }

    @DeleteMapping("/flights/{id}")
    void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }
}
