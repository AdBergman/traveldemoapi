package com.bergman.service.services;

import com.bergman.repository.entity.Flight;
import com.bergman.repository.repositories.FlightRepository;
import com.bergman.service.exceptions.FlightNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight saveFlight(Flight newFlight) {
        return flightRepository.save(newFlight);
    }

    public Flight getFlight(Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public Flight replaceFlight(Long id, Flight newFlight) {
        return flightRepository.findById(id)
                .map(flight -> {    //framework to solve this
                    flight.setOrigin(newFlight.getOrigin());
                    flight.setDestination(newFlight.getDestination());
                    flight.setAirline(newFlight.getAirline());
                    flight.setNumber(newFlight.getNumber());
                    return saveFlight(flight);
                })
                .orElseGet(() -> {
                    newFlight.setId(id);
                    return saveFlight(newFlight);
                });
    }
}
