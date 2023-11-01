package com.example.pizzaspringboot.center_mgmt.service;

import com.example.pizzaspringboot.center_mgmt.dto.CourseDTO;
import com.example.pizzaspringboot.center_mgmt.dto.CourseNameStartDateAndStudents;
import com.example.pizzaspringboot.center_mgmt.entities.Course;
import com.example.pizzaspringboot.center_mgmt.entities.Student;
import com.example.pizzaspringboot.center_mgmt.enums.CourseLevel;
import com.example.pizzaspringboot.center_mgmt.enums.Gender;
import com.example.pizzaspringboot.center_mgmt.repository.CourseRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTests {
    @Mock
    private CourseRepo courseRepo;

    @InjectMocks
    private CourseService courseService;

    private final Student student = new Student(UUID.randomUUID(), "Test", "Student", 18, Gender.MALE, "student@sample.com", "01234567890", 30000000000000L, null);
    private final Student student2 = new Student(UUID.randomUUID(), "Test2", "Student2", 18, Gender.MALE, "student@sample.com", "01234567890", 30000000000000L, null);
    private final Set<Student> studentSet = Stream.of(student, student2).collect(Collectors.toSet());

    private final Course course = new Course(UUID.randomUUID(), "Test Course", LocalDateTime.of(2023, 9, 26, 0, 0, 0), LocalDateTime.of(2023, 11, 26, 0, 0, 0), CourseLevel.Beginner, true, null, studentSet);
    private final CourseDTO courseDTO = new CourseDTO(course.getId(), "Test Course", course.getStartDate(), course.getEndDate(), CourseLevel.Beginner, true);
    private final Course course2 = new Course(UUID.randomUUID(), "Test Course2", course.getStartDate(), course.getEndDate(), CourseLevel.Middle, true, null, studentSet);
    private final CourseDTO courseDTO2 = new CourseDTO(course2.getId(), "Test Course2", course.getStartDate(), course.getEndDate(), CourseLevel.Middle, true);
    private final List<Course> courseList = new ArrayList<>(Arrays.asList(course, course2));
    private final List<CourseDTO> courseDTOList = new ArrayList<>(Arrays.asList(courseDTO, courseDTO2));
    private final List<String> studentNames = new ArrayList<>(Arrays.asList("David", "Youssef", "Muhammad"));
    private final CourseNameStartDateAndStudents s1 = new CourseNameStartDateAndStudents(course.getName(), course.getStartDate(), studentNames);
    private final CourseNameStartDateAndStudents s2 = new CourseNameStartDateAndStudents(course2.getName(), course2.getStartDate(), studentNames);
    private final List<CourseNameStartDateAndStudents> ls = new ArrayList<>(Arrays.asList(s1, s2));

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
        doReturn(courseList).when(courseRepo).findAll();

        List<CourseDTO> retrievedCourses = courseService.getAllCourses();

        Assertions.assertEquals(courseDTOList.get(0).getName(), retrievedCourses.get(0).getName());
        Assertions.assertEquals(courseDTOList.get(1).getName(), retrievedCourses.get(1).getName());
    }

    @Test
    public void CourseService_UpdateCourse_ReturnsUpdatedCourseDTO() {
        Course updatedCourse = new Course(course.getId(), "Test Course2", course.getStartDate(), course.getEndDate(), CourseLevel.Beginner, true, null, null);
        CourseDTO updatedCourseDTO = new CourseDTO(course.getId(), "Test Course2", course.getStartDate(), course.getEndDate(), CourseLevel.Beginner, true);

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

    @Test
    public void CourseService_GetCourseNameStartDateAndStudents_ReturnsListOfCourseNameStartDateAndStudents() {
        doReturn(courseList).when(courseRepo).findAll();
        List<CourseNameStartDateAndStudents> list = courseService.getCourseNameStartDateAndStudents();
        Assertions.assertNotNull(list);

    }
}
