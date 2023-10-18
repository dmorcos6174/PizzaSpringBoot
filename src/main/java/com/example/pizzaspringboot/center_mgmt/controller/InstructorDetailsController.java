package com.example.pizzaspringboot.center_mgmt.controller;

import com.example.pizzaspringboot.center_mgmt.dto.InstructorDetailsDTO;
import com.example.pizzaspringboot.center_mgmt.entities.InstructorDetails;
import com.example.pizzaspringboot.center_mgmt.service.InstructorDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class InstructorDetailsController {

    private final InstructorDetailsService instructorDetailsService;

    public InstructorDetailsController(InstructorDetailsService instructorDetailsService) {
        this.instructorDetailsService = instructorDetailsService;
    }

    @PostMapping("/instructors-details")
    public InstructorDetailsDTO createInstructorDetails(@RequestBody InstructorDetailsDTO instructorDetailsDTO) {
        return instructorDetailsService.createInstructorDetails(instructorDetailsDTO);
    }

    // Read
    @GetMapping("/instructors-details/{id}")
    public InstructorDetailsDTO getInstructorDetailsById(@PathVariable UUID id) {
        return instructorDetailsService.getInstructorDetailsById(id);
    }

    @GetMapping("/instructors-details")
    public List<InstructorDetailsDTO> getAllInstructorDetails() {
        return instructorDetailsService.getAllInstructorDetails();
    }

    // Update
    @PutMapping("/instructors-details/{id}")
    public InstructorDetailsDTO updateInstructorDetails(@PathVariable UUID id, @RequestBody InstructorDetailsDTO instructorDetailsDTO) {
        instructorDetailsDTO.setId(id);
        return instructorDetailsService.updateInstructorDetails(instructorDetailsDTO);
    }

    // Delete
    @DeleteMapping("/instructors-details/{id}")
    public void deleteInstructorDetails(@PathVariable UUID id) {
        instructorDetailsService.deleteInstructorDetails(id);
    }

}
