package com.example.pizzaspringboot.center_mgmt.service;

import com.example.pizzaspringboot.center_mgmt.dto.StudentDTO;
import com.example.pizzaspringboot.center_mgmt.entities.Instructor;
import com.example.pizzaspringboot.center_mgmt.entities.Student;
import com.example.pizzaspringboot.center_mgmt.enums.Gender;
import com.example.pizzaspringboot.center_mgmt.repository.StudentRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTests {
    @Mock
    private StudentRepo studentRepo;

    @InjectMocks
    private StudentService studentService;

    private Student student = new Student(UUID.randomUUID(), "Test", "Student", 18, Gender.MALE, "student@sample.com", "01234567890", 30000000000000L, null);
    private StudentDTO studentDTO = new StudentDTO(student.getId(), "Test", "Student", 18, Gender.MALE, "student@sample.com", "01234567890", 30000000000000L);

    @Test
    public void StudentService_CreateStudent_ReturnsStudentDTO() {
        doReturn(new ArrayList<>()).when(studentRepo).findByName(any(), any());
        doReturn(student).when(studentRepo).save(Mockito.any(Student.class));

        StudentDTO savedStudent = studentService.createStudent(studentDTO);

        Assertions.assertNotNull(savedStudent);
        Assertions.assertEquals(savedStudent.getId(), studentDTO.getId());
    }

    @Test
    public void StudentService_GetStudentById_ReturnsStudentDTO() {
        doReturn(Optional.of(student)).when(studentRepo).findById(any());

        StudentDTO retrievedStudent = studentService.getStudentById(student.getId());

        Assertions.assertNotNull(retrievedStudent);
        Assertions.assertEquals(retrievedStudent.getId(), studentDTO.getId());
    }

    @Test
    public void StudentService_GetAllStudents_ReturnsAllStudents() {
        Student student2 = new Student(UUID.randomUUID(), "Test2", "Student2", 18, Gender.MALE, "student@sample.com", "01234567890", 30000000000000L, null);
        StudentDTO studentDTO2 = new StudentDTO(student2.getId(), "Test2", "Student2", 18, Gender.MALE, "student@sample.com", "01234567890", 30000000000000L);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student2);

        List<StudentDTO> studentDTOList = new ArrayList<>();
        studentDTOList.add(studentDTO);
        studentDTOList.add(studentDTO2);

        doReturn(studentList).when(studentRepo).findAll();

        List<StudentDTO> retrievedStudents = studentService.getAllStudent();

        Assertions.assertEquals(studentDTOList.get(0).getFirstName(), retrievedStudents.get(0).getFirstName());
        Assertions.assertEquals(studentDTOList.get(0).getLastName(), retrievedStudents.get(0).getLastName());

        Assertions.assertEquals(studentDTOList.get(1).getFirstName(), retrievedStudents.get(1).getFirstName());
        Assertions.assertEquals(studentDTOList.get(1).getLastName(), retrievedStudents.get(1).getLastName());
    }

    @Test
    public void StudentService_UpdateStudent_ReturnsUpdatedStudentDTO() {
        Student updatedStudent = new Student(UUID.randomUUID(), "Test2", "Student2", 18, Gender.MALE, "student@sample.com", "01234567890", 30000000000000L, null);
        StudentDTO updatedStudentDTO = new StudentDTO(updatedStudent.getId(), "Test2", "Student2", 18, Gender.MALE, "student@sample.com", "01234567890", 30000000000000L);

        doReturn(Optional.of(student)).when(studentRepo).findById(any());
        doReturn(updatedStudent).when(studentRepo).save(Mockito.any(Student.class));

        StudentDTO savedStudentDTO = studentService.updateStudent(updatedStudentDTO);

        Assertions.assertNotNull(savedStudentDTO);
        Assertions.assertEquals(savedStudentDTO.getFirstName() + savedStudentDTO.getLastName(), updatedStudentDTO.getFirstName() + updatedStudentDTO.getLastName());
    }

    @Test
    public void StudentService_DeleteStudent_ReturnsTrue() {
        doReturn(Optional.of(student)).when(studentRepo).findById(any());
        doNothing().when(studentRepo).deleteById(any());
        doReturn(false).when(studentRepo).existsById(any());

        Assertions.assertTrue(studentService.deleteStudent(student.getId()));
    }
}
