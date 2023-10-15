package com.example.pizzaspringboot.center_mgmt.controller;

import com.example.pizzaspringboot.center_mgmt.dto.InstructorDTO;
import com.example.pizzaspringboot.center_mgmt.dto.StudentDTO;
import com.example.pizzaspringboot.center_mgmt.entities.Instructor;
import com.example.pizzaspringboot.center_mgmt.entities.Student;
import com.example.pizzaspringboot.center_mgmt.service.InstructorService;
import com.example.pizzaspringboot.center_mgmt.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }

    // Read
    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable UUID id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudent();
    }

    // Update
    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable UUID id, @RequestBody StudentDTO studentDTO) {
        studentDTO.setId(id);
        return studentService.updateStudent(studentDTO);
    }

    // Delete
    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
    }

}
