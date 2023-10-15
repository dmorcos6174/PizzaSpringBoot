package com.example.pizzaspringboot.center_mgmt.service;

import com.example.pizzaspringboot.center_mgmt.dto.InstructorDTO;
import com.example.pizzaspringboot.center_mgmt.entities.Instructor;
import com.example.pizzaspringboot.center_mgmt.repository.InstructorRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.pizzaspringboot.center_mgmt.util.EntityMapper.mapDTOToInstructor;

@Service
public class InstructorService {

    private InstructorRepo instructorRepo;

    public InstructorService(InstructorRepo instructorRepo) {
        this.instructorRepo = instructorRepo;
    }

    // Create
    public Instructor createInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = mapDTOToInstructor(instructorDTO);
        return instructorRepo.save(instructor);
    }

    // Read
    public Instructor getInstructorById(UUID id) {
        return instructorRepo.findById(id).orElse(null);
    }

    public List<Instructor> getAllInstructor() {
        return instructorRepo.findAll();
    }

    // Update
    public Instructor updateInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = instructorRepo.findById(instructorDTO.getId()).orElse(null);
        if (instructor != null) {
            instructor = mapDTOToInstructor(instructorDTO);
            instructorRepo.save(instructor);
        }

        return instructor;
    }

    // Delete
    public void deleteInstructor(UUID id) {
        instructorRepo.deleteById(id);
    }

}
