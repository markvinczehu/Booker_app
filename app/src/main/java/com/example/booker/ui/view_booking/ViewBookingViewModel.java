package com.example.booker.ui.view_booking;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.booker.model.Booking;
import com.example.booker.repository.booking.BookingRepository;
import com.example.booker.repository.booking.BookingRepositoryImpl;

import java.util.ArrayList;

public class ViewBookingViewModel extends AndroidViewModel {

    private BookingRepository bookingRepo;
    private ArrayList<Booking> bookings;

    public ViewBookingViewModel(@NonNull Application app) {
        super(app);
        bookingRepo = new BookingRepositoryImpl(app);
    }

    public LiveData<ArrayList<Booking>> getAllBookings() {
        return bookingRepo.getAllBookings();
    }
}


