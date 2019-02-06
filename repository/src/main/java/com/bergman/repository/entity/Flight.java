package com.bergman.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private @Id @GeneratedValue Long id;
    private String origin;
    private String destination;
    private String airline;
    private String number;
    private String departureTime;
    private String arrivalTime;
    private double priceTotal;
    private double taxesTotal;
    private String fareBasis;
}
