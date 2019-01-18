package com.bergman.repository.repositories.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Flight {

    private @Id @GeneratedValue Long id;
    private String origin;
    private String destination;
    private String airline;
    private String number;

    public Flight() {
    }

    public Flight(String origin, String destination, String airline, String number) {
        this.origin = origin;
        this.destination = destination;
        this.airline = airline;
        this.number = number;
    }
}
