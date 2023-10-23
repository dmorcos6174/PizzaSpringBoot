package com.example.pizzaspringboot.center_mgmt.service;

import com.example.pizzaspringboot.center_mgmt.dto.InstructorDTO;
import com.example.pizzaspringboot.center_mgmt.dto.InstructorNameAndCourses;
import com.example.pizzaspringboot.center_mgmt.dto.InstructorNameAndStudents;
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

    // JOINS
    public List<InstructorNameAndCourses> getInstructorNamesAndCourses() {
        List<Instructor> instructorList = instructorRepo.findAll();
        List<InstructorNameAndCourses> instructorNameAndCoursesList = new ArrayList<>();
        for (Instructor i : instructorList) {
            InstructorNameAndCourses x = new InstructorNameAndCourses(i.getFirstName(), i.getLastName(), i.getCourses());
            instructorNameAndCoursesList.add(x);
        }
        return instructorNameAndCoursesList;
    }

    public List<InstructorNameAndStudents> getInstructorNamesAndStudents() {
        List<Tuple> tuples = instructorRepo.findInstructorNamesAndStudents();
        List<InstructorNameAndStudents> instructorNameAndStudentsList = new ArrayList<>();
        for (Tuple t : tuples) {
            InstructorNameAndStudents x = new InstructorNameAndStudents(t.get(0, String.class), t.get(1, String.class), t.get(2, String.class), t.get(3, String.class));
            instructorNameAndStudentsList.add(x);
        }
        return instructorNameAndStudentsList;
    }

}
