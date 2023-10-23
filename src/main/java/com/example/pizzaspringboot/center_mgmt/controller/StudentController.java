package com.example.pizzaspringboot.center_mgmt.controller;

import com.example.pizzaspringboot.center_mgmt.dto.StudentDTO;
import com.example.pizzaspringboot.center_mgmt.dto.StudentName;
import com.example.pizzaspringboot.center_mgmt.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO);
    }

    // Read
    @GetMapping("/students/{id}")
    public StudentDTO getStudentById(@PathVariable UUID id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/students")
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudent();
    }

    // Update
    @PutMapping("/students/{id}")
    public StudentDTO updateStudent(@PathVariable UUID id, @RequestBody StudentDTO studentDTO) {
        studentDTO.setId(id);
        return studentService.updateStudent(studentDTO);
    }

    // Delete
    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
    }

    // JOINS

    @GetMapping("/students/middle-level")
    public List<StudentName> getStudentNamesInMiddleLevel() {
        return studentService.getStudentNamesInMiddleLevel();
    }
}
