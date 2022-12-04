package com.example.booker.repository.booking;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.booker.callback.Callback;
import com.example.booker.model.Booking;
import com.example.booker.persistence.booking.BookingDAO;
import com.example.booker.persistence.booking.BookingDAOImpl;
import com.example.booker.repository.user.UserRepository;
import com.example.booker.repository.user.UserRepositoryImpl;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class BookingRepositoryImpl implements BookingRepository, Callback, BookingDAOImpl.onInter {

    private BookingDAO bookingDAO;
    private UserRepository userRepository;
    private PropertyChangeSupport support;
    private MutableLiveData<ArrayList<Booking>> mutableLiveData;

    public BookingRepositoryImpl(Application app) {
        bookingDAO = new BookingDAOImpl(this, this);
        userRepository =  new UserRepositoryImpl(app);
        support = new PropertyChangeSupport(this);
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public void requestBooking(Booking booking) {
        bookingDAO.requestBooking(booking);
    }

    @Override
    public LiveData<ArrayList<Booking>> getAllBookings() {
        getBookings();
        return mutableLiveData;
    }

    private void getBookings() {
        bookingDAO.getBookings();
    }

    @Override
    public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
        support.addPropertyChangeListener(name, listener);
    }

    @Override
    public void removerPropertyChangeListener(String name, PropertyChangeListener listener) {
        support.removePropertyChangeListener(name, listener);
    }

    @Override
    public void getMessage(String message) {
        support.firePropertyChange("MessageFromFirebase", null, message);
    }

    @Override
    public void bookings(ArrayList<Booking> bookings) {
        mutableLiveData.setValue(bookings);
    }
}
