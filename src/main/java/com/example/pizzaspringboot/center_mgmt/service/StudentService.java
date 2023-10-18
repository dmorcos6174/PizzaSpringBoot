package com.example.pizzaspringboot.center_mgmt.service;

import com.example.pizzaspringboot.center_mgmt.dto.InstructorDTO;
import com.example.pizzaspringboot.center_mgmt.dto.StudentDTO;
import com.example.pizzaspringboot.center_mgmt.entities.Instructor;
import com.example.pizzaspringboot.center_mgmt.entities.Student;
import com.example.pizzaspringboot.center_mgmt.exception.AlreadyExistsException;
import com.example.pizzaspringboot.center_mgmt.exception.NotFoundException;
import com.example.pizzaspringboot.center_mgmt.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.pizzaspringboot.center_mgmt.util.EntityMapper.*;

@Service
public class StudentService {

    private final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    // Create
    public StudentDTO createStudent(StudentDTO studentDTO) throws AlreadyExistsException {
        if(studentRepo.findByName(studentDTO.getFirstName(), studentDTO.getLastName()).isEmpty()) {
            throw new AlreadyExistsException("Student with name: " + studentDTO.getFirstName() + " " + studentDTO.getLastName() + "already exists");
        }

        Student student = mapDTOToStudent(studentDTO);
        studentRepo.save(student);
        return mapStudentToDTO(student);
    }

    // Read
    public StudentDTO getStudentById(UUID id) throws NotFoundException {
        Optional<Student> studentOptional = studentRepo.findById(id);
        if (studentOptional.isEmpty()) {
            throw new NotFoundException("No Student with such id exists");
        }
        return mapStudentToDTO(studentOptional.get());
    }

    public List<StudentDTO> getAllStudent() {
        List<Student> studentList = studentRepo.findAll();
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for (Student s : studentList) {
            studentDTOList.add(mapStudentToDTO(s));
        }
        return studentDTOList;
    }

    // Update
    public StudentDTO updateStudent(StudentDTO studentDTO) throws NotFoundException {
        Optional<Student> studentOptional = studentRepo.findById(studentDTO.getId());
        if (studentOptional.isEmpty()) {
            throw new NotFoundException("No Student with such id exists");
        }
        Student savedStudent = studentOptional.get();
        studentRepo.save(savedStudent);
        return mapStudentToDTO(savedStudent);
    }

    // Delete
    public void deleteStudent(UUID id) {
        Optional<Student> studentOptional = studentRepo.findById(id);
        if (studentOptional.isEmpty()) {
            throw new NotFoundException("No Student with such id exists");
        }
        studentRepo.deleteById(id);
    }
}
