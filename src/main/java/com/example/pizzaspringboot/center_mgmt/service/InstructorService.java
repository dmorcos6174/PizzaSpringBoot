package com.example.pizzaspringboot.center_mgmt.service;

import com.example.pizzaspringboot.center_mgmt.dto.InstructorDTO;
import com.example.pizzaspringboot.center_mgmt.dto.InstructorAndCourses;
import com.example.pizzaspringboot.center_mgmt.dto.InstructorAndStudents;
import com.example.pizzaspringboot.center_mgmt.entities.Instructor;
import com.example.pizzaspringboot.center_mgmt.exception.AlreadyExistsException;
import com.example.pizzaspringboot.center_mgmt.exception.NotFoundException;
import com.example.pizzaspringboot.center_mgmt.repository.InstructorRepo;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.pizzaspringboot.center_mgmt.mapper.EntityMapper.mapDTOToInstructor;
import static com.example.pizzaspringboot.center_mgmt.mapper.EntityMapper.mapInstructorToDTO;

@Service
public class InstructorService {

    private final InstructorRepo instructorRepo;

    public InstructorService(InstructorRepo instructorRepo) {
        this.instructorRepo = instructorRepo;
    }

    // Create
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) throws AlreadyExistsException {
        if (!instructorRepo.findByName(instructorDTO.getFirstName(), instructorDTO.getLastName()).isEmpty()) {
            throw new AlreadyExistsException("Instructor with name: " + instructorDTO.getFirstName() + " " + instructorDTO.getLastName() + "already exists");
        }

        Instructor savedInstructor = instructorRepo.save(mapDTOToInstructor(instructorDTO));
        return mapInstructorToDTO(savedInstructor);
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
        Instructor updatedInstructor = instructorRepo.save(mapDTOToInstructor(instructorDTO));
        return mapInstructorToDTO(updatedInstructor);
    }

    // Delete
    public boolean deleteInstructor(UUID id) {
        Optional<Instructor> instructorOptional = instructorRepo.findById(id);
        if (instructorOptional.isEmpty()) {
            throw new NotFoundException("No Instructor with such id exists");
        }
        instructorRepo.deleteById(id);
        return !instructorRepo.existsById(id);
    }

    // JOINS
    public List<InstructorAndCourses> getInstructorNamesAndCourses() {
        List<Instructor> instructorList = instructorRepo.findAll();
        List<InstructorAndCourses> instructorAndCoursesList = new ArrayList<>();
        for (Instructor i : instructorList) {
            InstructorAndCourses x = new InstructorAndCourses(i.getFirstName(), i.getLastName(), i.getCourses());
            instructorAndCoursesList.add(x);
        }
        return instructorAndCoursesList;
    }

    public List<InstructorAndStudents> getInstructorNamesAndStudents() {
        List<Tuple> tuples = instructorRepo.findInstructorNamesAndStudents();
        List<InstructorAndStudents> instructorAndStudentsList = new ArrayList<>();
        for (Tuple t : tuples) {
            InstructorAndStudents x = new InstructorAndStudents(t.get(0, String.class), t.get(1, String.class), t.get(2, String.class), t.get(3, String.class));
            instructorAndStudentsList.add(x);
        }
        return instructorAndStudentsList;
    }
}
