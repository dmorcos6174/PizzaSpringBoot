package com.example.pizzaspringboot.center_mgmt.service;

import com.example.pizzaspringboot.center_mgmt.dto.CourseDTO;
import com.example.pizzaspringboot.center_mgmt.dto.CourseNameStartDateAndStudents;
import com.example.pizzaspringboot.center_mgmt.entity.Course;
import com.example.pizzaspringboot.center_mgmt.entity.Student;
import com.example.pizzaspringboot.center_mgmt.enums.CourseLevel;
import com.example.pizzaspringboot.center_mgmt.enums.Gender;
import com.example.pizzaspringboot.center_mgmt.exception.AlreadyExistsException;
import com.example.pizzaspringboot.center_mgmt.exception.NotFoundException;
import com.example.pizzaspringboot.center_mgmt.repository.CourseRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTests {
    @Mock
    private CourseRepo courseRepo;

    @InjectMocks
    private CourseService courseService;

    private static final Student student = new Student(UUID.randomUUID(), "Test", "Student", 18, Gender.MALE, "student@sample.com", "01234567890", 30000000000000L, null);
    private static final Student student2 = new Student(UUID.randomUUID(), "Test2", "Student2", 18, Gender.MALE, "student@sample.com", "01234567890", 30000000000000L, null);
//    private final Set<Student> studentSet = Stream.of(student2, student).collect(Collectors.toSet());
    private static final Set<Student> studentSet = new HashSet<>();
    private final Course course = new Course(UUID.randomUUID(), "Test Course", LocalDateTime.of(2023, 9, 26, 0, 0, 0), LocalDateTime.of(2023, 11, 26, 0, 0, 0), CourseLevel.Beginner, true, null, studentSet);
    private final CourseDTO courseDTO = new CourseDTO(course.getId(), "Test Course", course.getStartDate(), course.getEndDate(), CourseLevel.Beginner, true);
    private final Course course2 = new Course(UUID.randomUUID(), "Test Course2", course.getStartDate(), course.getEndDate(), CourseLevel.Middle, true, null, studentSet);
    private final CourseDTO courseDTO2 = new CourseDTO(course2.getId(), "Test Course2", course.getStartDate(), course.getEndDate(), CourseLevel.Middle, true);
    private final List<Course> courseList = new ArrayList<>(Arrays.asList(course, course2));
    private final List<CourseDTO> courseDTOList = new ArrayList<>(Arrays.asList(courseDTO, courseDTO2));

    @BeforeAll
    public static void setUp() {
        studentSet.add(student);
        studentSet.add(student2);
    }

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
    public void CourseService_CreateCourse_ThrowsAlreadyExistsException() {
        Optional<Course> optional = Optional.of(course);
        Optional<Course> optional1 = Optional.of(course2);
        List<Optional<Course>> optionalList = new ArrayList<>(Arrays.asList(optional, optional1));

        doReturn(optionalList).when(courseRepo).findByName(any());

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            courseService.createCourse(courseDTO);
        });
    }

    @Test
    public void CourseService_GetCourseById_ReturnsCourseDTO() {
        doReturn(Optional.of(course)).when(courseRepo).findById(any());

        CourseDTO retrievedCourse = courseService.getCourseById(course.getId());

        Assertions.assertNotNull(retrievedCourse);
        Assertions.assertEquals(retrievedCourse.getId(), courseDTO.getId());
    }

    @Test
    public void CourseService_GetCourseById_ThrowsNotFoundException() {
        doReturn(Optional.empty()).when(courseRepo).findById(any());

        Assertions.assertThrows(NotFoundException.class, () -> {
            courseService.getCourseById(courseDTO.getId());
        });
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
    public void CourseService_UpdateCourse_ThrowsNotFoundException() {
        doReturn(Optional.empty()).when(courseRepo).findById(any());

        Assertions.assertThrows(NotFoundException.class, () -> {
            courseService.updateCourse(courseDTO);
        });
    }

    @Test
    public void CourseService_DeleteCourse_ReturnsTrue() {
        doReturn(Optional.of(course)).when(courseRepo).findById(any());
        doNothing().when(courseRepo).deleteById(any());
        doReturn(false).when(courseRepo).existsById(any());

        Assertions.assertTrue(courseService.deleteCourse(course.getId()));
    }

    @Test
    public void CourseService_DeleteCourse_ThrowsNotFoundException() {
        doReturn(Optional.empty()).when(courseRepo).findById(any());

        Assertions.assertThrows(NotFoundException.class, () -> {
            courseService.deleteCourse(courseDTO.getId());
        });
    }

    @Test
    public void CourseService_GetCourseNameStartDateAndStudents_ReturnsListOfCourseNameStartDateAndStudents() {
        doReturn(courseList).when(courseRepo).findAll();
        List<CourseNameStartDateAndStudents> list = courseService.getCourseNameStartDateAndStudents();
        Assertions.assertNotNull(list);
        Assertions.assertEquals(course.getName(), list.get(0).getCourseName());
        Assertions.assertEquals(course2.getName(), list.get(1).getCourseName());
        Assertions.assertEquals(course.getStartDate(), list.get(0).getCourseDate());
        Assertions.assertEquals(course2.getStartDate(), list.get(1).getCourseDate());
        Assertions.assertEquals(student.getFirstName() + " " + student.getLastName(), list.get(0).getStudentNames().get(0));
        Assertions.assertEquals(student2.getFirstName() + " " + student2.getLastName(), list.get(0).getStudentNames().get(1));
    }
}
