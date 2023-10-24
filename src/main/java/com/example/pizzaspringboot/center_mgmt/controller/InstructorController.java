package com.example.pizzaspringboot.center_mgmt.controller;

import com.example.pizzaspringboot.center_mgmt.dto.InstructorDTO;
import com.example.pizzaspringboot.center_mgmt.dto.InstructorAndCourses;
import com.example.pizzaspringboot.center_mgmt.dto.InstructorAndStudents;
import com.example.pizzaspringboot.center_mgmt.service.InstructorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping("/instructors")
    public InstructorDTO createInstructor(@RequestBody InstructorDTO instructorDTO) {
        return instructorService.createInstructor(instructorDTO);
    }

    // Read
    @GetMapping("/instructors/{id}")
    public InstructorDTO getInstructorById(@PathVariable UUID id) {
        return instructorService.getInstructorById(id);
    }

    @GetMapping("/instructors")
    public List<InstructorDTO> getAllInstructors() {
        return instructorService.getAllInstructor();
    }

    // Update
    @PutMapping("/instructors/{id}")
    public InstructorDTO updateInstructor(@PathVariable UUID id, @RequestBody InstructorDTO instructorDTO) {
        instructorDTO.setId(id);
        return instructorService.updateInstructor(instructorDTO);
    }

    // Delete
    @DeleteMapping("/instructors/{id}")
    public void deleteInstructor(@PathVariable UUID id) {
        instructorService.deleteInstructor(id);
    }

    // JOINS
    @GetMapping("/instructors/names-courses")
    public List<InstructorAndCourses> getInstructorNamesAndCourses() {
        return instructorService.getInstructorNamesAndCourses();
    }

    @GetMapping("/instructors/names-students")
    public List<InstructorAndStudents> getInstructorNamesAndStudents() {
        return instructorService.getInstructorNamesAndStudents();
    }
}
