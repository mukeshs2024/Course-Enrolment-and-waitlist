package com.courseportal.service;

import com.courseportal.model.Course;
import com.courseportal.model.Enrollment;
import com.courseportal.model.Student;
import com.courseportal.repository.CourseRepository;
import com.courseportal.repository.EnrollmentRepository;
import com.courseportal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Enrollment enrollStudent(Integer studentId, Integer courseId) {
        // Find student and course, or throw exception if not found
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        
        // FIX 1: Correctly find the course by its ID and use the correct type 'Course'.
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        // FIX 2: Use 'countBy...' method to get a number (long), not 'findBy...'.
        long currentEnrollmentCount = enrollmentRepository.findByCourseCourseIdAndStatus(courseId, "Enrolled");

        Enrollment newEnrollment = new Enrollment();
        newEnrollment.setStudent(student);
        newEnrollment.setCourse(course); // FIX 3: Pass the correct 'Course' object.
        newEnrollment.setEnrolledOn(LocalDateTime.now());

        // FIX 4: Call getMaxCapacity() on the 'Course' object.
        if (currentEnrollmentCount < course.getMaxCapacity()) {
            newEnrollment.setStatus("Enrolled");
        } else {
            newEnrollment.setStatus("Waitlisted");
        }

        return enrollmentRepository.save(newEnrollment);
    }

    public List<Enrollment> getCoursesByStudent(Integer studentId) {
        return enrollmentRepository.findByStudentStudentId(studentId);
    }

    public List<Enrollment> getStudentsByCourse(Integer courseId) {
        return enrollmentRepository.findByCourseCourseId(courseId);
    }

    // This method gets the actual waitlisted student records.
    public List<Enrollment> getWaitlistedStudentsForCourse(Integer courseId) {
        return enrollmentRepository.countByCourseCourseIdAndStatus(courseId, "Waitlisted");
    }
}