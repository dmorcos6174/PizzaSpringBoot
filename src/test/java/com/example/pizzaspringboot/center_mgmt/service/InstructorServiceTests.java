package com.example.pizzaspringboot.center_mgmt.service;

import com.example.pizzaspringboot.center_mgmt.dto.InstructorDTO;
import com.example.pizzaspringboot.center_mgmt.entities.Instructor;
import com.example.pizzaspringboot.center_mgmt.exception.NotFoundException;
import com.example.pizzaspringboot.center_mgmt.repository.InstructorRepo;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class InstructorServiceTests {
    @Mock
    private InstructorRepo instructorRepo;

    @InjectMocks
    private InstructorService instructorService;

    private final Instructor instructor = new Instructor(UUID.randomUUID(), "Test", "Instructor", "instructor@sample.com", "01234567890", null, null);
    private final InstructorDTO instructorDTO = new InstructorDTO(instructor.getId(), "Test", "Instructor", "instructor@sample.com", "01234567890");
    private final Instructor instructor2 = new Instructor(UUID.randomUUID(), "Test", "Instructor2", "instructor@sample.com", "01234567890", null, null);
    private final InstructorDTO instructorDTO2 = new InstructorDTO(instructor2.getId(), "Test", "Instructor2", "instructor@sample.com", "01234567890");
    private final List<Instructor> instructorList = new ArrayList<>(Arrays.asList(instructor, instructor2));
    private final List<InstructorDTO> instructorDTOList = new ArrayList<>(Arrays.asList(instructorDTO, instructorDTO2));

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
    public void InstructorService_Get_GetInstructorById_ThrowsNotFoundException() {
        doReturn(Optional.empty()).when(instructorRepo).findById(any());

        Assertions.assertThrows(NotFoundException.class, () -> {
            instructorService.getInstructorById(instructorDTO.getId());
        });
    }

    @Test
    public void InstructorService_GetAllInstructors_ReturnsAllInstructors() {
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

    @Test
    public void InstructorService_DeleteInstructor_ThrowsNotFoundException() {
        doReturn(Optional.empty()).when(instructorRepo).findById(any());

        Assertions.assertThrows(NotFoundException.class, () -> {
            instructorService.deleteInstructor(instructorDTO.getId());
        });
    }
}
