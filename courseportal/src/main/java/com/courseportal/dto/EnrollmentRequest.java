package com.courseportal.dto;

// A simple class to map the JSON request body for enrollment
public class EnrollmentRequest {
    private Integer studentId;
    private Integer courseId;

    // Getters and Setters
    public Integer getStudentId() {
        return studentId;
    }
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    public Integer getCourseId() {
        return courseId;
    }
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}