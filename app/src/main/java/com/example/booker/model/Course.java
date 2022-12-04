package com.example.booker.model;

public class Course {
    private String name;
    private String contents;
    private String startDate;
    private String duration;

    public Course() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setEndDate(String duration) {
        this.duration = duration;
    }
}
