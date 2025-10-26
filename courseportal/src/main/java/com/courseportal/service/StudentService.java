package com.courseportal.service;

import com.courseportal.model.Student;
import com.courseportal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(Student student) {
        student.setJoinDate(LocalDateTime.now());
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    // ----- THIS IS THE NEW METHOD YOU ADDED -----
    public Student getStudentById(Integer studentId) {
        return studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
    }
    // ---------------------------------------------
}