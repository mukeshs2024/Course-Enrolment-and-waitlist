package com.courseportal.repository;

import com.courseportal.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    // Finds all enrollments (enrolled or waitlisted) for a specific student
    List<Enrollment> findByStudentStudentId(Integer studentId);

    // Finds all enrollments (enrolled or waitlisted) for a specific course
    List<Enrollment> findByCourseCourseId(Integer courseId);

    // Finds enrollments for a course that match a specific status (e.g., "Waitlisted")
    long findByCourseCourseIdAndStatus(Integer courseId, String status);

    // Counts enrollments for a course that match a specific status (e.g., "Enrolled")
    long countByCourseCourseIdAndStatus(Integer courseId, String status);

	
}