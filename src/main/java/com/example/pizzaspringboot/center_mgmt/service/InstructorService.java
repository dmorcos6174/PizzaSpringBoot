package com.example.pizzaspringboot.center_mgmt.service;

import com.example.pizzaspringboot.center_mgmt.dto.CourseDTO;
import com.example.pizzaspringboot.center_mgmt.dto.InstructorDTO;
import com.example.pizzaspringboot.center_mgmt.entities.Course;
import com.example.pizzaspringboot.center_mgmt.entities.Instructor;
import com.example.pizzaspringboot.center_mgmt.exception.AlreadyExistsException;
import com.example.pizzaspringboot.center_mgmt.exception.NotFoundException;
import com.example.pizzaspringboot.center_mgmt.repository.InstructorRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.pizzaspringboot.center_mgmt.util.EntityMapper.*;

@Service
public class InstructorService {

    private final InstructorRepo instructorRepo;

    public InstructorService(InstructorRepo instructorRepo) {
        this.instructorRepo = instructorRepo;
    }

    // Create
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) throws AlreadyExistsException {
        if(instructorRepo.findByName(instructorDTO.getFirstName(), instructorDTO.getLastName()).isEmpty()) {
            throw new AlreadyExistsException("Instructor with name: " + instructorDTO.getFirstName() + " " + instructorDTO.getLastName() + "already exists");
        }

        Instructor instructor = mapDTOToInstructor(instructorDTO);
        instructorRepo.save(instructor);
        return mapInstructorToDTO(instructor);
    }

    // Read
    public InstructorDTO getInstructorById(UUID id) throws NotFoundException {
        Optional<Instructor> instructorOptional = instructorRepo.findById(id);
        if (instructorOptional.isEmpty()) {
            throw new NotFoundException("No Instructor with such id exists");
        }
        return mapInstructorToDTO(instructorOptional.get());
    }

    public List<InstructorDTO> getAllInstructor() {
        List<Instructor> instructorList = instructorRepo.findAll();
        List<InstructorDTO> instructorDTOList = new ArrayList<>();
        for (Instructor i : instructorList) {
            instructorDTOList.add(mapInstructorToDTO(i));
        }
        return instructorDTOList;
    }

    // Update
    public InstructorDTO updateInstructor(InstructorDTO instructorDTO) throws NotFoundException {
        Optional<Instructor> instructorOptional = instructorRepo.findById(instructorDTO.getId());
        if (instructorOptional.isEmpty()) {
            throw new NotFoundException("No Instructor with such id exists");
        }
        Instructor savedInstructor = instructorOptional.get();
        instructorRepo.save(savedInstructor);
        return mapInstructorToDTO(savedInstructor);
    }

    // Delete
    public void deleteInstructor(UUID id) {
        Optional<Instructor> instructorOptional = instructorRepo.findById(id);
        if (instructorOptional.isEmpty()) {
            throw new NotFoundException("No Instructor with such id exists");
        }
        instructorRepo.deleteById(id);
    }

}
