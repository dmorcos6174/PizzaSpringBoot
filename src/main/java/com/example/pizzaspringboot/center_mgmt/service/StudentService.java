package com.example.pizzaspringboot.center_mgmt.service;

import com.example.pizzaspringboot.center_mgmt.dto.StudentDTO;
import com.example.pizzaspringboot.center_mgmt.entities.Student;
import com.example.pizzaspringboot.center_mgmt.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.pizzaspringboot.center_mgmt.util.EntityMapper.*;

@Service
public class StudentService {

    private StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    // Create
    public Student createStudent(StudentDTO studentDTO) {
        Student student = mapDTOToStudent(studentDTO);
        return studentRepo.save(student);
    }

    // Read
    public Student getStudentById(UUID id) {
        return studentRepo.findById(id).orElse(null);
    }

    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    // Update
    public Student updateStudent(StudentDTO studentDTO) {
        Student student = studentRepo.findById(studentDTO.getId()).orElse(null);
        if (student != null) {
            student = mapDTOToStudent(studentDTO);
            studentRepo.save(student);
        }

        return student;
    }

    // Delete
    public void deleteStudent(UUID id) {
        studentRepo.deleteById(id);
    }

}
