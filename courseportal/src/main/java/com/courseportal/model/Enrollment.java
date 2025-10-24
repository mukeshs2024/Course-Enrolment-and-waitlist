package com.courseportal.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Integer enrollmentId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "enrolled_on", nullable = false)
    private LocalDateTime enrolledOn;

    @Column(name = "status", nullable = false, length = 20)
    private String status; // "Enrolled" or "Waitlisted"

    // Default constructor
    public Enrollment() {
    }

    // Getters and Setters
    public Integer getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Integer enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getEnrolledOn() {
        return enrolledOn;
    }

    public void setEnrolledOn(LocalDateTime enrolledOn) {
        this.enrolledOn = enrolledOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public void setStudent1(Student student2) {
		// TODO Auto-generated method stub
		
	}
}