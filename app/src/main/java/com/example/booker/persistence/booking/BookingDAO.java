package com.example.booker.persistence.booking;

import com.example.booker.model.Booking;

import java.util.List;

public interface BookingDAO {
    void requestBooking(Booking booking);

    List<String> getTime();

    void getBookings();
}
