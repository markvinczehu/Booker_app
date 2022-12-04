package com.example.booker.ui.add_course;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.booker.model.Course;
import com.example.booker.repository.course.CourseRepository;
import com.example.booker.repository.course.CourseRepositoryImpl;

import java.beans.PropertyChangeEvent;

public class AddCourseViewModel extends AndroidViewModel {

    private CourseRepository courseRepo;

    public AddCourseViewModel(@NonNull Application app) {
        super(app);
        courseRepo = new CourseRepositoryImpl(app);
        courseRepo.addPropertyChangeListener("MessageFromFirebase", this::message);
    }

    private void message(PropertyChangeEvent evt) {
        String mess = (String) evt.getNewValue();
        Toast.makeText(getApplication(), mess, Toast.LENGTH_SHORT).show();
    }

    public void addCourse(String name, String content, String startDate, String  endDate) {
        if(!name.trim().equals("") && !content.trim().equals("") && !startDate.trim().equals("") && !endDate.trim().equals("")) {
            Course course = new Course();
            course.setName(name.trim());
            course.setContents(content.trim());
            course.setStartDate(startDate.trim());
            course.setEndDate(endDate.trim());

            courseRepo.addCourse(course);
        }
        else Toast.makeText(getApplication(), "Please fill out all the fields", Toast.LENGTH_SHORT).show();
    }
}
