package com.example.booker.repository.booking;

import androidx.lifecycle.LiveData;

import com.example.booker.model.Booking;
import com.example.booker.property_change.PropertyChangeSubject;

import java.util.ArrayList;


public interface BookingRepository extends PropertyChangeSubject {
    void requestBooking(Booking booking);

    LiveData<ArrayList<Booking>> getAllBookings();
}
