package com.example.pizzaspringboot.center_mgmt.service;

import com.example.pizzaspringboot.center_mgmt.dto.CourseDTO;
import com.example.pizzaspringboot.center_mgmt.entities.Course;
import com.example.pizzaspringboot.center_mgmt.repository.CourseRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.pizzaspringboot.center_mgmt.util.EntityMapper.mapDTOToCourse;

@Service
public class CourseService {

    private CourseRepo courseRepo;

    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    // Create
    public Course createCourse(CourseDTO courseDTO) {
        Course course = mapDTOToCourse(courseDTO);
        return courseRepo.save(course);
    }

    // Read
    public Course getCourseById(UUID id) {
        return courseRepo.findById(id).orElse(null);
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    // Update
    public Course updateCourse(CourseDTO courseDTO) {
        Course course = courseRepo.findById(courseDTO.getId()).orElse(null);
        if (course != null) {
            course = mapDTOToCourse(courseDTO);
            courseRepo.save(course);
        }

        return course;
    }

    // Delete
    public void deleteCourse(UUID id) {
        courseRepo.deleteById(id);
    }

}
