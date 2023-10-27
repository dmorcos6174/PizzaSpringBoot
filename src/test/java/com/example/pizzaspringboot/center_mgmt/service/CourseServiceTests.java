package com.example.pizzaspringboot.center_mgmt.service;

import com.example.pizzaspringboot.center_mgmt.dto.CourseDTO;
import com.example.pizzaspringboot.center_mgmt.entities.Course;
import com.example.pizzaspringboot.center_mgmt.enums.CourseLevel;
import com.example.pizzaspringboot.center_mgmt.repository.CourseRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTests {
    @Mock
    private CourseRepo courseRepo;

    @InjectMocks
    private CourseService courseService;

    @Test
    public void CourseService_CreateCourse_ReturnsCourseDTO() {
        Course course = new Course(UUID.randomUUID(), "Test Course", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Beginner, true, null, null);
        CourseDTO courseDTO = new CourseDTO(UUID.randomUUID(), "Test Course", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Beginner, true);

        when(courseRepo.save(Mockito.any(Course.class))).thenReturn(course);
//        doReturn(course).when(courseRepo).save(Mockito.any(Course.class));

        CourseDTO savedCourse = courseService.createCourse(courseDTO);

//        verify(courseRepo, times(1)).save(course);

        Assertions.assertNotNull(savedCourse);
    }
}
