package com.example.pizzaspringboot.center_mgmt.service;

import com.example.pizzaspringboot.center_mgmt.dto.InstructorDetailsDTO;
import com.example.pizzaspringboot.center_mgmt.entities.InstructorDetails;
import com.example.pizzaspringboot.center_mgmt.repository.InstructorDetailsRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.pizzaspringboot.center_mgmt.util.EntityMapper.mapDTOToInstructorDetails;

@Service
public class InstructorDetailsService {

    private InstructorDetailsRepo instructorDetailsRepo;

    public InstructorDetailsService(InstructorDetailsRepo instructorDetailsRepo) {
        this.instructorDetailsRepo = instructorDetailsRepo;
    }

    // Create
    public InstructorDetails createInstructorDetails(InstructorDetailsDTO instructorDetailsDTO) {
        InstructorDetails instructorDetails = mapDTOToInstructorDetails(instructorDetailsDTO);
        return instructorDetailsRepo.save(instructorDetails);
    }

    // Read
    public InstructorDetails getInstructorDetailsById(UUID id) {
        return instructorDetailsRepo.findById(id).orElse(null);
    }

    public List<InstructorDetails> getAllInstructorDetails() {
        return instructorDetailsRepo.findAll();
    }

    // Update
    public InstructorDetails updateInstructorDetails(InstructorDetailsDTO instructorDetailsDTO) {
        InstructorDetails instructorDetails = instructorDetailsRepo.findById(instructorDetailsDTO.getId()).orElse(null);
        if (instructorDetails != null) {
            instructorDetails = mapDTOToInstructorDetails(instructorDetailsDTO);
            instructorDetailsRepo.save(instructorDetails);
        }

        return instructorDetails;
    }

    // Delete
    public void deleteInstructorDetails(UUID id) {
        instructorDetailsRepo.deleteById(id);
    }

}
