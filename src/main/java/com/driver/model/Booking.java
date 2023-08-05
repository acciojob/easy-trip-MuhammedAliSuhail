package com.driver.model;

public class Booking {

    private int bookingId;
    private int passangerId;
    private int flightId;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getPassangerId() {
        return passangerId;
    }

    public void setPassangerId(int passangerId) {
        this.passangerId = passangerId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public Booking(int bookingId, int passangerId, int flightId) {
        this.bookingId = bookingId;
        this.passangerId = passangerId;
        this.flightId = flightId;
    }
}
