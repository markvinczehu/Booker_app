package com.example.booker.ui.add_course;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.booker.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class AddCourse extends Fragment {

    private AddCourseViewModel viewModel;
    private TextInputEditText name;
    private TextInputEditText startDate;
    private TextInputEditText duration;
    private TextInputEditText content;
    private Button addCourse;
    DatePickerDialog.OnDateSetListener setListener;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_course, container, false);

        initViews();

        viewModel = new ViewModelProvider(this).get(AddCourseViewModel.class);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener,
                        year, month, day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String selectedDate = dayOfMonth + "/" + month + "/" + year;
                startDate.setText(selectedDate);
            }
        };

        addCourse.setOnClickListener(v -> add());
        return view;
    }

    private void initViews() {
        name = view.findViewById(R.id.input_course_name);
        content = view.findViewById(R.id.input_content);
        startDate = view.findViewById(R.id.start_date);
        duration = view.findViewById(R.id.end_date);
        addCourse = view.findViewById(R.id.save);
    }

    private void add() {
        viewModel.addCourse(name.getText().toString(), content.getText().toString(),
                startDate.getText().toString(), duration.getText().toString());
    }
}