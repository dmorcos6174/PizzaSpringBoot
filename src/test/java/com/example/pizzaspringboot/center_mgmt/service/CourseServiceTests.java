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
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.pizzaspringboot.center_mgmt.mapper.EntityMapper.*;

import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTests {
    @Mock
    private CourseRepo courseRepo;

    @InjectMocks
    private CourseService courseService;

    private Course course = new Course(UUID.randomUUID(), "Test Course", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Beginner, true, null, null);
    private CourseDTO courseDTO = new CourseDTO(course.getId(), "Test Course", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Beginner, true);

    @Test
    public void CourseService_CreateCourse_ReturnsCourseDTO() {
        doReturn(new ArrayList<>()).when(courseRepo).findByName(any());
        doReturn(course).when(courseRepo).save(Mockito.any(Course.class));

        CourseDTO savedCourse = courseService.createCourse(courseDTO);

//        verify(courseRepo, times(1)).save(course);
        Assertions.assertNotNull(savedCourse);
        Assertions.assertEquals(savedCourse.getId(), courseDTO.getId());
    }

    @Test
    public void CourseService_GetCourseById_ReturnsCourseDTO() {
        doReturn(Optional.of(course)).when(courseRepo).findById(any());

        CourseDTO retrievedCourse = courseService.getCourseById(course.getId());

        Assertions.assertNotNull(retrievedCourse);
        Assertions.assertEquals(retrievedCourse.getId(), courseDTO.getId());
    }

    @Test
    public void CourseService_GetAllCourses_ReturnsAllCourses() {
        Course course2 = new Course(UUID.randomUUID(), "Test Course2", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Middle, true, null, null);
        CourseDTO courseDTO2 = new CourseDTO(course2.getId(), "Test Course2", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Middle, true);

        List<Course> courseList = new ArrayList<>();
        courseList.add(course);
        courseList.add(course2);

        List<CourseDTO> courseDTOList = new ArrayList<>();
        courseDTOList.add(courseDTO);
        courseDTOList.add(courseDTO2);

        doReturn(courseList).when(courseRepo).findAll();

        List<CourseDTO> retrievedCourses = courseService.getAllCourses();

        Assertions.assertEquals(courseDTOList.get(0).getName(), retrievedCourses.get(0).getName());
        Assertions.assertEquals(courseDTOList.get(1).getName(), retrievedCourses.get(1).getName());
    }

    @Test
    public void CourseService_UpdateCourse_ReturnsUpdatedCourseDTO() {
        Course updatedCourse = new Course(course.getId(), "Test Course2", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Beginner, true, null, null);
        CourseDTO updatedCourseDTO = new CourseDTO(course.getId(), "Test Course2", new Date(2023, 9, 26), new Date(2023, 11, 26), CourseLevel.Beginner, true);

        doReturn(Optional.of(course)).when(courseRepo).findById(any());
        doReturn(updatedCourse).when(courseRepo).save(Mockito.any(Course.class));

        CourseDTO savedCourseDTO = courseService.updateCourse(updatedCourseDTO);

        Assertions.assertNotNull(savedCourseDTO);
        Assertions.assertEquals(savedCourseDTO.getName(), updatedCourseDTO.getName());
    }

    @Test
    public void CourseService_DeleteCourse_ReturnsTrue() {
        doReturn(Optional.of(course)).when(courseRepo).findById(any());
        doNothing().when(courseRepo).deleteById(any());
        doReturn(false).when(courseRepo).existsById(any());

        Assertions.assertTrue(courseService.deleteCourse(course.getId()));
    }
}
