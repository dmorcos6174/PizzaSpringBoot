package com.example.pizzaspringboot.center_mgmt.service;

import com.example.pizzaspringboot.center_mgmt.dto.InstructorDetailsDTO;
import com.example.pizzaspringboot.center_mgmt.entities.Instructor;
import com.example.pizzaspringboot.center_mgmt.entities.InstructorDetails;
import com.example.pizzaspringboot.center_mgmt.exception.NotFoundException;
import com.example.pizzaspringboot.center_mgmt.repository.InstructorDetailsRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.pizzaspringboot.center_mgmt.mapper.EntityMapper.mapDTOToInstructorDetails;
import static com.example.pizzaspringboot.center_mgmt.mapper.EntityMapper.mapInstructorDetailsToDTO;

@Service
public class InstructorDetailsService {

    private final InstructorDetailsRepo instructorDetailsRepo;

    public InstructorDetailsService(InstructorDetailsRepo instructorDetailsRepo) {
        this.instructorDetailsRepo = instructorDetailsRepo;
    }

    // Create
    public InstructorDetailsDTO createInstructorDetails(InstructorDetailsDTO instructorDetailsDTO) {
        InstructorDetails savedInstDetails = instructorDetailsRepo.save(mapDTOToInstructorDetails(instructorDetailsDTO));
        return mapInstructorDetailsToDTO(savedInstDetails);
    }

    // Read
    public InstructorDetailsDTO getInstructorDetailsById(UUID id) throws NotFoundException {
        Optional<InstructorDetails> instructorDetailsOptional = instructorDetailsRepo.findById(id);
        if (instructorDetailsOptional.isEmpty()){
            throw new NotFoundException("No Instructor Details with such id exists");
        }
        return mapInstructorDetailsToDTO(instructorDetailsOptional.get());
    }

    public List<InstructorDetailsDTO> getAllInstructorDetails() {
        List<InstructorDetails> instructorDetailsList = instructorDetailsRepo.findAll();
        List<InstructorDetailsDTO> instructorDetailsDTOList = new ArrayList<>();
        for (InstructorDetails i : instructorDetailsList) {
            instructorDetailsDTOList.add(mapInstructorDetailsToDTO(i));
        }
        return instructorDetailsDTOList;
    }

    // Update
    public InstructorDetailsDTO updateInstructorDetails(InstructorDetailsDTO instructorDetailsDTO) throws NotFoundException {
        Optional<InstructorDetails> instructorDetailsOptional = instructorDetailsRepo.findById(instructorDetailsDTO.getId());
        if (instructorDetailsOptional.isEmpty()) {
            throw new NotFoundException("No Instructor Details with such id exists");
        }
        InstructorDetails updatedInstDetails = instructorDetailsRepo.save(mapDTOToInstructorDetails(instructorDetailsDTO));
        return mapInstructorDetailsToDTO(updatedInstDetails);
    }

    // Delete
    public boolean deleteInstructorDetails(UUID id) {
        Optional<InstructorDetails> instructorDetailsOptional = instructorDetailsRepo.findById(id);
        if (instructorDetailsOptional.isEmpty()) {
            throw new NotFoundException("No Instructor Details with such id exists");
        }
        instructorDetailsRepo.deleteById(id);
        return !instructorDetailsRepo.existsById(id);
    }
}
