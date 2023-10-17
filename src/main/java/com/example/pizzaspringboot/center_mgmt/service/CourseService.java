package com.example.pizzaspringboot.center_mgmt.service;

import com.example.pizzaspringboot.center_mgmt.dto.CourseDTO;
import com.example.pizzaspringboot.center_mgmt.entities.Course;
import com.example.pizzaspringboot.center_mgmt.exception.AlreadyExistsException;
import com.example.pizzaspringboot.center_mgmt.exception.NotFoundException;
import com.example.pizzaspringboot.center_mgmt.repository.CourseRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.pizzaspringboot.center_mgmt.util.EntityMapper.mapDTOToCourse;

@Service
public class CourseService {

    private CourseRepo courseRepo;

    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    // Create
    public Course createCourse(CourseDTO courseDTO) throws AlreadyExistsException {
        if (!courseRepo.findByName(courseDTO.getName()).isEmpty()) {
            throw new AlreadyExistsException("Course with name: " + courseDTO.getName() + " already exists");
        }
        Course course = mapDTOToCourse(courseDTO);
        return courseRepo.save(course);
    }

    // Read
    public Course getCourseById(UUID id) throws NotFoundException {
        Optional<Course> courseOptional = courseRepo.findById(id);
        if (courseOptional.isEmpty()){
            throw new NotFoundException("No Course with such id exists");
        }
        return courseOptional.get();
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    // Update
    public Course updateCourse(CourseDTO courseDTO) throws NotFoundException {
        Optional<Course> courseOptional = courseRepo.findById(courseDTO.getId());
        if (courseOptional.isEmpty()) {
            throw new NotFoundException("No Course with such id exists");
        }
        Course savedCourse = courseOptional.get();
        courseRepo.save(savedCourse);
        return savedCourse;
    }

    // Delete
    public void deleteCourse(UUID id) throws NotFoundException {
        Optional<Course> courseOptional = courseRepo.findById(id);
        if (courseOptional.isEmpty()) {
            throw new NotFoundException("No Course with such id exists");
        }
        courseRepo.deleteById(id);
    }
}
