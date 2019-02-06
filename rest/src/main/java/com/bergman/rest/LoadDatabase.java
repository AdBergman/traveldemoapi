package com.bergman.rest;

import com.bergman.repository.entity.Flight;
import com.bergman.repository.repositories.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(FlightRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(
                    new Flight().builder()
                            .origin("STO").destination("LON").airline("BA").number("100")
                            .departureTime("Mon").arrivalTime("Tue")
                            .priceTotal(100.00).taxesTotal(20.00).fareBasis("ABC123")
                            .build()));

            log.info("Preloading " + repository.save(
                    new Flight().builder()
                            .origin("LON").destination("STO").airline("BA").number("101")
                            .departureTime("Tue").arrivalTime("Wed")
                            .priceTotal(100.00).taxesTotal(20.00).fareBasis("ABC124")
                            .build()));
        };
    }
}
