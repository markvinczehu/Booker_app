package com.example.booker.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booker.model.Booking;
import com.example.booker.R;

import java.util.ArrayList;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    List<Booking> bookings;

    public BookingAdapter() {
    }

    @NonNull
    @Override
    public BookingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.booking_items, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.ViewHolder holder, int position) {
        holder.user.setText(bookings.get(position).getUsername().toString());
        holder.dateTime.setText(bookings.get(position).getDate() + " " + bookings.get(position).getTime());
        holder.specialRequest.setText(bookings.get(position).getSpecialRequest());
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    public void setBookingItems(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView user;
        TextView dateTime;
        TextView specialRequest;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.username);
            dateTime = itemView.findViewById(R.id.date_time);
            specialRequest = itemView.findViewById(R.id.request);
        }
    }
}
