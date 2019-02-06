package com.bergman.service.services;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOffer;
import com.bergman.repository.entity.Flight;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AvailService {
    @Value("${AMADEUS_CLIENT_ID}")
    private String AMADEUS_CLIENT_ID;
    @Value("${AMADEUS_CLIENT_SECRET}")
    private String AMADEUS_CLIENT_SECRET;

    public List<Flight> flightSearch(Params searchRequest) throws ResponseException {
        List<Flight> results = new ArrayList<>();
        Amadeus amadeus = Amadeus
                .builder(AMADEUS_CLIENT_ID, AMADEUS_CLIENT_SECRET)
                .build();

            //For NonStop Only
             FlightOffer[] flightOffers = amadeus.shopping.flightOffers.get(searchRequest);
             for (FlightOffer flightOffer : flightOffers){
                 FlightOffer.FlightSegment flightSegment = flightOffer.getOfferItems()[0].getServices()[0].getSegments()[0].getFlightSegment();
                 FlightOffer.Price price = flightOffer.getOfferItems()[0].getPrice();

                 results.add(new Flight().builder()
                         .origin(flightSegment.getDeparture().getIataCode())
                         .destination(flightSegment.getArrival().getIataCode())
                         .airline(flightSegment.getCarrierCode())
                         .number(flightSegment.getNumber())
                         .departureTime(flightSegment.getDeparture().getAt())
                         .arrivalTime(flightSegment.getArrival().getAt())
                         .priceTotal(price.getTotal())
                         .taxesTotal(price.getTotalTaxes())
                         .fareBasis(flightOffer.getOfferItems()[0].getServices()[0].getSegments()[0].getPricingDetailPerAdult().getFareBasis())
                         .build());
             }

        return results;
    }
}
