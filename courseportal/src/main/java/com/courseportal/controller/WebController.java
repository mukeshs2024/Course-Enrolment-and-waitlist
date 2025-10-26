package com.courseportal.controller;

// ----- EXISTING IMPORTS -----
import com.courseportal.model.Student;
import com.courseportal.service.StudentService;
import com.courseportal.model.Course;
import com.courseportal.service.CourseService;

// ----- ALL REQUIRED IMPORTS -----
import com.courseportal.service.EnrollmentService;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import com.courseportal.model.Enrollment;
import org.springframework.web.bind.annotation.RequestParam; // <-- ADD THIS IMPORT
// ---------------------------------

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    @Autowired
    private CourseService courseService;
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private EnrollmentService enrollmentService;
    
    
    // This is your existing method
    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }
    
    // This is your existing method
    @GetMapping("/courses")
    public String showCoursesPage(Model model) {
        model.addAttribute("allCourses", courseService.getAllCourses());
        model.addAttribute("newCourse", new Course());
        return "courses";
    }
    
    // This is your existing method
    @PostMapping("/courses/add")
    public String addCourse(@ModelAttribute Course newCourse) {
        courseService.createCourse(newCourse);
        return "redirect:/courses";
    }

    // This is your existing method
    @GetMapping("/students")
    public String showStudentsPage(Model model) {
        model.addAttribute("allStudents", studentService.getAllStudents());
        model.addAttribute("newStudent", new Student());
        return "students"; // This tells Spring to load "students.html"
    }

    // This is your existing method
    @PostMapping("/students/add")
    public String addStudent(@ModelAttribute Student newStudent) {
        studentService.addStudent(newStudent);
        return "redirect:/students";
    }
    
    
    // This is your existing method
    @GetMapping("/student/{id}")
    public String showStudentDetailPage(@PathVariable("id") Integer studentId, Model model) {
        
        Student student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);
        
        List<Enrollment> enrollments = enrollmentService.getCoursesByStudent(studentId);
        model.addAttribute("enrollments", enrollments);
        
        return "student-detail"; // This will load student-detail.html
    }
    
    // ----- ADD THESE TWO NEW METHODS FOR ENROLLMENT -----
    
    /**
     * This method shows the new enrollment page.
     * It finds the student and a list of all courses to choose from.
     */
    @GetMapping("/enroll/{studentId}")
    public String showEnrollmentPage(@PathVariable("studentId") Integer studentId, Model model) {
        
        // 1. Get the student so we can display their name
        model.addAttribute("student", studentService.getStudentById(studentId));
        
        // 2. Get all courses to show in the dropdown
        model.addAttribute("allCourses", courseService.getAllCourses());
        
        // 3. Return the new HTML page
        return "enroll"; // This will load enroll.html
    }

    /**
     * This method handles the form submission from the enroll.html page.
     * It re-uses the EnrollmentService you already built!
     */
    @PostMapping("/enroll")
    public String processEnrollment(
                    @RequestParam("studentId") Integer studentId,
                    @RequestParam("courseId") Integer courseId) {
        
        // 1. Use the existing service to enroll the student
        try {
            enrollmentService.enrollStudent(studentId, courseId);
        } catch (Exception e) {
            // Handle errors later (e.g., student already enrolled)
            System.out.println("Error enrolling: " + e.getMessage());
        }
        
        // 2. Redirect back to the student's detail page
        return "redirect:/student/" + studentId;
    }
    // ----------------------------------------------------

}