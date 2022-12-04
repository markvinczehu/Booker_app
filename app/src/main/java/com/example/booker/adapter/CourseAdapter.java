package com.example.booker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booker.model.Course;
import com.example.booker.R;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    ArrayList<Course> courses;
    onListItemListener listener;

    public CourseAdapter(onListItemListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.course_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        holder.courseName.setText(courses.get(position).getName());
        holder.startDate.setText(courses.get(position).getStartDate());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setCourseItems(ArrayList<Course> courseItems) {
        courses = courseItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView courseName;
        TextView startDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.course_name);
            startDate = itemView.findViewById(R.id.start_date);

            itemView.setOnClickListener(view -> listener.onClicked(courses.get(getAdapterPosition())));
        }
    }

    public interface onListItemListener {
        void onClicked(Course course);
    }
}
