package com.courseportal.controller;

// ----- ADD THESE IMPORTS -----
import com.courseportal.model.Student;
import com.courseportal.service.StudentService;
// -------------------------------

import com.courseportal.model.Course;
import com.courseportal.service.CourseService;
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
    
    // ----- ADD THIS SECTION -----
    @Autowired
    private StudentService studentService;
    // ----------------------------
    
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

    // ----- ADD THESE TWO NEW METHODS -----

    // This method will handle requests to http://localhost:8082/students
    @GetMapping("/students")
    public String showStudentsPage(Model model) {
        // 1. Get all students from the service
        model.addAttribute("allStudents", studentService.getAllStudents());
        
        // 2. Add a new empty student object for the "Add Student" form
        model.addAttribute("newStudent", new Student());
        
        // 3. Return the name of the HTML file to load
        return "students"; // This tells Spring to load "students.html"
    }

    // This method will handle the "Add Student" form submission
    @PostMapping("/students/add")
    public String addStudent(@ModelAttribute Student newStudent) {
        // Get the 'newStudent' object from the form and save it
        studentService.addStudent(newStudent);
        
        // Redirect the user back to the /students page to see the new list
        return "redirect:/students";
    }
    // -------------------------------------

} // <-- This is the final closing brace of your class