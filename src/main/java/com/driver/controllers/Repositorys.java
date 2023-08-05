package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.Booking;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository
public class Repositorys {
int bookingId;

    public Repositorys() {
        this.bookingId=0;
    }

    HashMap<String, Airport> AirportsDB=new HashMap<>();
     HashMap<Date, List<Flight>> FlightDB=new HashMap<java.util.Date, List<Flight>>();

     HashMap<Integer, Passenger> PassangerDB=new HashMap<>();

     HashMap<Integer, Booking> bookingDB=new HashMap<>();

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
}
