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
import com.example.pizzaspringboot.center_mgmt.mapper.EntityMapper.*;

import java.util.*;

import static com.example.pizzaspringboot.center_mgmt.mapper.EntityMapper.mapCourseToDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        CourseDTO courseDTO = new CourseDTO(course.getId(), "Test Course", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Beginner, true);
//        CourseDTO courseDTO = mapCourseToDTO(course);

//        when(courseRepo.save(Mockito.any(Course.class))).thenReturn(course);
        doReturn(course).when(courseRepo).save(Mockito.any(Course.class));

        CourseDTO savedCourse = courseService.createCourse(courseDTO);

//        verify(courseRepo, times(1)).save(course);

        Assertions.assertNotNull(savedCourse);
        assertEquals(savedCourse.getId(), courseDTO.getId());
    }

    @Test
    public void CourseService_GetCourseById_ReturnsCourseDTO() {
        Course course = new Course(UUID.randomUUID(), "Test Course", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Beginner, true, null, null);
        CourseDTO courseDTO = new CourseDTO(course.getId(), "Test Course", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Beginner, true);

        doReturn(Optional.of(course)).when(courseRepo).findById(any());

        CourseDTO retrievedCourse = courseService.getCourseById(course.getId());

        Assertions.assertNotNull(retrievedCourse);
        assertEquals(retrievedCourse.getId(), courseDTO.getId());
    }

    @Test
    public void CourseService_GetAllCourses_ReturnsAllCourses() {
        Course course = new Course(UUID.randomUUID(), "Test Course", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Beginner, true, null, null);
        Course course2 = new Course(UUID.randomUUID(), "Test Course2", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Middle, true, null, null);

        CourseDTO courseDTO = new CourseDTO(course.getId(), "Test Course", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Beginner, true);
        CourseDTO courseDTO2 = new CourseDTO(course2.getId(), "Test Course2", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Middle, true);

        List<Course> courseList = new ArrayList<>();
        courseList.add(course);
        courseList.add(course2);

        List<CourseDTO> courseDTOList = new ArrayList<>();
        courseDTOList.add(courseDTO);
        courseDTOList.add(courseDTO2);

        doReturn(courseList).when(courseRepo).findAll();

        List<CourseDTO> retrievedCourses = courseService.getAllCourses();

        assertEquals(courseDTOList.get(0).getName(), retrievedCourses.get(0).getName());
        assertEquals(courseDTOList.get(1).getName(), retrievedCourses.get(1).getName());
    }

    @Test
    public void CourseService_UpdateCourse_ReturnsUpdatedCourseDTO() {
        Course course = new Course(UUID.randomUUID(), "Test Course", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Beginner, true, null, null);
        Course updatedCourse = new Course(course.getId(), "Test Course2", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Beginner, true, null, null);
        CourseDTO courseDTO = new CourseDTO(course.getId(), "Test Course", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Beginner, true);
        CourseDTO updatedCourseDTO = new CourseDTO(course.getId(), "Test Course2", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Beginner, true);

        doReturn(Optional.of(course)).when(courseRepo).findById(any());
        doReturn(updatedCourse).when(courseRepo).save(Mockito.any(Course.class));

        CourseDTO savedCourseDTO = courseService.updateCourse(updatedCourseDTO);

        Assertions.assertNotNull(savedCourseDTO);
        assertEquals(savedCourseDTO.getName(), updatedCourse.getName());
    }

    @Test
    public void CourseService_DeleteCourse_ReturnsTrue() {
        Course course = new Course(UUID.randomUUID(), "Test Course", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Beginner, true, null, null);
//        CourseDTO courseDTO = new CourseDTO(course.getId(), "Test Course", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Beginner, true);

        doReturn(Optional.of(course)).when(courseRepo).findById(any());
        doNothing().when(courseRepo).deleteById(any());
        doReturn(false).when(courseRepo).existsById(any());

        assertTrue(courseService.deleteCourse(course.getId()));
    }
}
