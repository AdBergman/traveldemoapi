package com.bergman.repository.repositories;

import com.bergman.repository.repositories.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
