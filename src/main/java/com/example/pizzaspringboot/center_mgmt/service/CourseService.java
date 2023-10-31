package com.example.pizzaspringboot.center_mgmt.service;

import com.example.pizzaspringboot.center_mgmt.dto.CourseDTO;
import com.example.pizzaspringboot.center_mgmt.dto.CourseNameStartDateAndStudents;
import com.example.pizzaspringboot.center_mgmt.dto.CourseOfLevelAndStudents;
import com.example.pizzaspringboot.center_mgmt.entities.Course;
import com.example.pizzaspringboot.center_mgmt.entities.Student;
import com.example.pizzaspringboot.center_mgmt.exception.AlreadyExistsException;
import com.example.pizzaspringboot.center_mgmt.exception.NotFoundException;
import com.example.pizzaspringboot.center_mgmt.repository.CourseRepo;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.pizzaspringboot.center_mgmt.mapper.EntityMapper.mapCourseToDTO;
import static com.example.pizzaspringboot.center_mgmt.mapper.EntityMapper.mapDTOToCourse;

@Service
public class CourseService {

    private final CourseRepo courseRepo;

    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    // Create
    public CourseDTO createCourse(CourseDTO courseDTO) throws AlreadyExistsException {
        if (!courseRepo.findByName(courseDTO.getName()).isEmpty()) {
            throw new AlreadyExistsException("Course with name: " + courseDTO.getName() + " already exists");
        }
        Course savedCourse = courseRepo.save(mapDTOToCourse(courseDTO));
        return mapCourseToDTO(savedCourse);
    }

    // Read
    public CourseDTO getCourseById(UUID id) throws NotFoundException {
        Optional<Course> courseOptional = courseRepo.findById(id);
        if (courseOptional.isEmpty()){
            throw new NotFoundException("No Course with such id exists");
        }
        return mapCourseToDTO(courseOptional.get());
    }

    public List<CourseDTO> getAllCourses() {
        List<Course> courseList = courseRepo.findAll();
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (Course c : courseList) {
            courseDTOList.add(mapCourseToDTO(c));
        }
        return courseDTOList;
    }

    // Update
    public CourseDTO updateCourse(CourseDTO courseDTO) throws NotFoundException {
        Optional<Course> courseOptional = courseRepo.findById(courseDTO.getId());
        if (courseOptional.isEmpty()) {
            throw new NotFoundException("No Course with such id exists");
        }
        Course updatedCourse = courseRepo.save(mapDTOToCourse(courseDTO));
        return mapCourseToDTO(updatedCourse);
    }

    // Delete
    public boolean deleteCourse(UUID id) throws NotFoundException {
        Optional<Course> courseOptional = courseRepo.findById(id);
        if (courseOptional.isEmpty()) {
            throw new NotFoundException("No Course with such id exists");
        }
        courseRepo.deleteById(id);
        return !courseRepo.existsById(id);
    }

    // JOINS
        public List<CourseNameStartDateAndStudents> getCourseNameStartDateAndStudents() {
        List<Course> courseList = courseRepo.findAll();
        List<CourseNameStartDateAndStudents> courseNameStartDateAndStudentsList = new ArrayList<>();
        for (Course c : courseList) {
            Set<Student> students = c.getStudents();
            List<String> studentNames = new ArrayList<>();
            for (Student s : students) {
                studentNames.add(s.getFirstName() + s.getLastName());
            }
            CourseNameStartDateAndStudents x = new CourseNameStartDateAndStudents(c.getName(), c.getStartDate(), studentNames);
            courseNameStartDateAndStudentsList.add(x);
        }
        return courseNameStartDateAndStudentsList;
    }

    /*public List<CourseNameStartDateAndStudents> getCourseNameStartDateAndStudents() {
        List<Tuple> tuples = courseRepo.findCourseNameStartDateAndStudents();
        List<CourseNameStartDateAndStudents> instructorNameAndStudentsList = new ArrayList<>();
        for (Tuple t : tuples) {
            CourseNameStartDateAndStudents x = new CourseNameStartDateAndStudents(t.get(0, String.class), t.get(1, Date.class), t.get(2, String.class), t.get(3, String.class));
            instructorNameAndStudentsList.add(x);
        }
        return instructorNameAndStudentsList;
    }*/

    public List<CourseOfLevelAndStudents> getCoursesOfLevelAndStudents() {
        return courseRepo.findCoursesOfLevelAndStudents();
    }
}
