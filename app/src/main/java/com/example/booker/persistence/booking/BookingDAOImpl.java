package com.example.booker.persistence.booking;


import android.util.Log;


import androidx.lifecycle.LiveData;

import com.example.booker.callback.Callback;
import com.example.booker.model.Booking;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class BookingDAOImpl extends LiveData<Booking> implements BookingDAO {

    private FirebaseFirestore db;
    private Callback callBack;
    onInter onInter;

    public BookingDAOImpl(Callback callBack, onInter onInter) {
        db = FirebaseFirestore.getInstance();
        this.callBack = callBack;
        this.onInter = onInter;
    }


    @Override
    public List<String> getTime() {
        List<String> time = new ArrayList<>();
        time.add("10:00");
        time.add("10:30");
        time.add("11:00");
        time.add("11:30");
        time.add("12:00");
        time.add("12:30");
        time.add("13:00");
        time.add("13:30");
        time.add("14:00");
        time.add("14:30");
        return time;
    }

    @Override
    public void requestBooking(Booking booking) {
        db.collection("bookings")
                .add(booking)
                .addOnSuccessListener(documentReference ->
                        callBack.getMessage("Booked successfully")
                )
                .addOnFailureListener(
                        e -> Log.w(" ", "Error adding the document", e)
                );
    }

    @Override
    public void getBookings() {
        ArrayList<Booking> bookings = new ArrayList<>();

        db.collection("bookings")
                .addSnapshotListener((value, error) -> {
                    assert value != null;
                    for(DocumentSnapshot ds: value.getDocuments()) {
                        Booking booking =  ds.toObject(Booking.class);
                        bookings.add(booking);
                        onInter.bookings(bookings);
                    }
                });
    }

    public interface onInter {
        void bookings(ArrayList<Booking> bookings);
    }
}
