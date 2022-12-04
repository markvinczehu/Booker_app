package com.example.booker.ui.view_courses;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.booker.adapter.CourseAdapter;
import com.example.booker.model.Course;
import com.example.booker.R;

import java.util.ArrayList;


public class ViewCourses extends Fragment implements CourseAdapter.onListItemListener {

    ViewCourseViewModel viewModel;
    RecyclerView recyclerView;
    CourseAdapter adapter;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_view_courses, container, false);

        viewModel = new ViewModelProvider(this).get(ViewCourseViewModel.class);

        recyclerView = view.findViewById(R.id.courseList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.hasFixedSize();

        adapter = new CourseAdapter(this);

        getAllCourses();

        return view;
    }

    private void getAllCourses() {
        viewModel.getAllCourses().observe(getViewLifecycleOwner(), new Observer<ArrayList<Course>>() {
            @Override
            public void onChanged(ArrayList<Course> courses) {
                adapter.setCourseItems(courses);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onClicked(Course course) {

    }
}