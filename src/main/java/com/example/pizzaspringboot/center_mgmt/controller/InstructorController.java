package com.example.pizzaspringboot.center_mgmt.controller;

import com.example.pizzaspringboot.center_mgmt.dto.InstructorDTO;
import com.example.pizzaspringboot.center_mgmt.entities.Instructor;
import com.example.pizzaspringboot.center_mgmt.service.InstructorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class InstructorController {

    private InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping("/instructors")
    public Instructor createInstructor(@RequestBody InstructorDTO instructorDTO) {
        return instructorService.createInstructor(instructorDTO);
    }

    // Read
    @GetMapping("/instructors/{id}")
    public Instructor getInstructorById(@PathVariable UUID id) {
        return instructorService.getInstructorById(id);
    }

    @GetMapping("/instructors")
    public List<Instructor> getAllInstructors() {
        return instructorService.getAllInstructor();
    }

    // Update
    @PutMapping("/instructors/{id}")
    public Instructor updateInstructor(@PathVariable UUID id, @RequestBody InstructorDTO instructorDTO) {
        instructorDTO.setId(id);
        return instructorService.updateInstructor(instructorDTO);
    }

    // Delete
    @DeleteMapping("/instructors/{id}")
    public void deleteInstructor(@PathVariable UUID id) {
        instructorService.deleteInstructor(id);
    }
}
