package com.example.booker.ui.booking;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.booker.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;

public class BookAppointment extends Fragment {

    private BookAppointmentViewModel viewModel;
    private Button book;
    private Button cancel;
    private TextView datePicker;
    private ListView bookingTime;
    private TextInputEditText specialRequest;
    private String pickedTime;
    DatePickerDialog.OnDateSetListener setListener;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_book_time, container, false);

        viewModel = new ViewModelProvider(this).get(BookAppointmentViewModel.class);

        initViews();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getTime());
        bookingTime.setAdapter(adapter);

        bookingTime.setOnItemClickListener((adapterView, view, i, l) -> {
            String selectedTime = getTime().get(i);
            pickedTime = selectedTime;
        });

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        datePicker.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener,
                    year, month, day
            );
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        });

        setListener = (view, year1, month1, dayOfMonth) -> {
            month1 = month1 +1;
            String selectedDate = dayOfMonth + "/" + month1 + "/" + year1;
            datePicker.setText(selectedDate);
        };

        book.setOnClickListener(v -> onBookClicked());
        cancel.setOnClickListener(v -> onCancelClicked());

        return view;
    }

    private void initViews() {
        book = view.findViewById(R.id.button_book);
        cancel = view.findViewById(R.id.button_cancel);
        datePicker = view.findViewById(R.id.date_picker);
        specialRequest = view.findViewById(R.id.special_request);
        bookingTime = view.findViewById(R.id.time_list);
    }

    private void onCancelClicked() {

    }

    private void onBookClicked() {
        viewModel.getCurrentUser().observe(getViewLifecycleOwner(), user ->
                viewModel.requestBooking(datePicker.getText().toString(), pickedTime, specialRequest.getText().toString(), user.getEmail()));
    }

    private List<String> getTime() {
        return viewModel.getTime();
    }
}