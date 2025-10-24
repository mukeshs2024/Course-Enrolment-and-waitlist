package com.courseportal.service;

import com.courseportal.model.Course;
import com.courseportal.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Course course) {
        course.setCreatedAt(LocalDateTime.now());
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }
}