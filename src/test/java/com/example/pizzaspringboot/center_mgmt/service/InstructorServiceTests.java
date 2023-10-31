package com.example.pizzaspringboot.center_mgmt.service;

import com.example.pizzaspringboot.center_mgmt.dto.InstructorDTO;
import com.example.pizzaspringboot.center_mgmt.entities.Instructor;
import com.example.pizzaspringboot.center_mgmt.repository.InstructorRepo;
import jakarta.inject.Inject;
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
public class InstructorServiceTests {
    @Mock
    private InstructorRepo instructorRepo;

    @InjectMocks
    private InstructorService instructorService;

    private Instructor instructor = new Instructor(UUID.randomUUID(), "Test", "Instructor", "instructor@sample.com", "01234567890", null, null);
    private InstructorDTO instructorDTO = new InstructorDTO(instructor.getId(), "Test", "Instructor", "instructor@sample.com", "01234567890");

    @Test
    public void InstructorService_CreateInstructor_ReturnsInstructorDTO() {
        doReturn(new ArrayList<>()).when(instructorRepo).findByName(any(), any());
        doReturn(instructor).when(instructorRepo).save(Mockito.any(Instructor.class));

        InstructorDTO savedInstructor = instructorService.createInstructor(instructorDTO);

        Assertions.assertNotNull(savedInstructor);
        Assertions.assertEquals(savedInstructor.getId(), instructorDTO.getId());
    }

    @Test
    public void InstructorService_GetInstructorById_ReturnsInstructorDTO() {
        doReturn(Optional.of(instructor)).when(instructorRepo).findById(any());

        InstructorDTO retrievedInstructor = instructorService.getInstructorById(instructor.getId());

        Assertions.assertNotNull(retrievedInstructor);
        Assertions.assertEquals(retrievedInstructor.getId(), instructorDTO.getId());
    }

    @Test
    public void InstructorService_GetAllInstructors_ReturnsAllInstructors() {
        Instructor instructor2 = new Instructor(UUID.randomUUID(), "Test", "Instructor2", "instructor@sample.com", "01234567890", null, null);
        InstructorDTO instructorDTO2 = new InstructorDTO(instructor2.getId(), "Test", "Instructor2", "instructor@sample.com", "01234567890");

        List<Instructor> instructorList = new ArrayList<>();
        instructorList.add(instructor);
        instructorList.add(instructor2);

        List<InstructorDTO> instructorDTOList = new ArrayList<>();
        instructorDTOList.add(instructorDTO);
        instructorDTOList.add(instructorDTO2);

        doReturn(instructorList).when(instructorRepo).findAll();

        List<InstructorDTO> retrievedInstructors = instructorService.getAllInstructor();

        Assertions.assertEquals(instructorDTOList.get(0).getFirstName(), retrievedInstructors.get(0).getFirstName());
        Assertions.assertEquals(instructorDTOList.get(0).getLastName(), retrievedInstructors.get(0).getLastName());

        Assertions.assertEquals(instructorDTOList.get(1).getFirstName(), retrievedInstructors.get(1).getFirstName());
        Assertions.assertEquals(instructorDTOList.get(1).getLastName(), retrievedInstructors.get(1).getLastName());
    }

    @Test
    public void InstructorService_UpdateInstructor_ReturnsUpdatedInstructorDTO() {
        Instructor updatedInst = new Instructor(instructor.getId(), "Test", "Instructor2", "instructor@sample.com", "01234567890", null, null);
        InstructorDTO updatedInstDTO = new InstructorDTO(instructor.getId(), "Test", "Instructor2", "instructor@sample.com", "01234567890");

        doReturn(Optional.of(instructor)).when(instructorRepo).findById(any());
        doReturn(updatedInst).when(instructorRepo).save(Mockito.any(Instructor.class));

        InstructorDTO savedInstructorDTO = instructorService.updateInstructor(updatedInstDTO);

        Assertions.assertNotNull(savedInstructorDTO);
        Assertions.assertEquals(savedInstructorDTO.getFirstName() + savedInstructorDTO.getLastName(), updatedInstDTO.getFirstName() + updatedInstDTO.getLastName());
    }

    @Test
    public void InstructorService_DeleteInstructor_ReturnsTrue() {
        doReturn(Optional.of(instructor)).when(instructorRepo).findById(any());
        doNothing().when(instructorRepo).deleteById(any());
        doReturn(false).when(instructorRepo).existsById(any());

        Assertions.assertTrue(instructorService.deleteInstructor(instructor.getId()));
    }
}
