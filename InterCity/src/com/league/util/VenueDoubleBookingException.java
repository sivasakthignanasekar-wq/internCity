package com.league.util;

public class VenueDoubleBookingException extends Exception {
    public String toString() {
        return "Venue already booked for this date and time";
    }
}
