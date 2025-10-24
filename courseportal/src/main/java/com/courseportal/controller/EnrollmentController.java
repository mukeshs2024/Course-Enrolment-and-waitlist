package com.courseportal.controller;

import com.courseportal.dto.EnrollmentRequest;
import com.courseportal.model.Enrollment;
import com.courseportal.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping
    public Enrollment enrollStudent(@RequestBody EnrollmentRequest request) {
        return enrollmentService.enrollStudent(request.getStudentId(), request.getCourseId());
    }

    @GetMapping("/student/{id}")
    public List<Enrollment> getCoursesByStudent(@PathVariable("id") Integer studentId) {
        return enrollmentService.getCoursesByStudent(studentId);
    }

    @GetMapping("/course/{id}")
    public List<Enrollment> getStudentsByCourse(@PathVariable("id") Integer courseId) {
        return enrollmentService.getStudentsByCourse(courseId);
    }

    // This matches the '/api/waitlist/course/{id}' endpoint from your diagram
    @GetMapping("/waitlist/course/{id}")
    public List<Enrollment> getWaitlistedStudents(@PathVariable("id") Integer courseId) {
        return enrollmentService.getWaitlistedStudentsForCourse(courseId);
    }
}