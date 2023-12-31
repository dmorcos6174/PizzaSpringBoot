package com.example.pizzaspringboot.center_mgmt.controller;

import com.example.pizzaspringboot.center_mgmt.dto.CourseDTO;
import com.example.pizzaspringboot.center_mgmt.dto.CourseNameStartDateAndStudents;
import com.example.pizzaspringboot.center_mgmt.dto.CourseOfLevelAndStudents;
import com.example.pizzaspringboot.center_mgmt.exception.AlreadyExistsException;
import com.example.pizzaspringboot.center_mgmt.exception.NotFoundException;
import com.example.pizzaspringboot.center_mgmt.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Create
    @PostMapping("/courses")
    public CourseDTO createCourse(@RequestBody CourseDTO courseDTO) throws AlreadyExistsException {
        return courseService.createCourse(courseDTO);
    }

    // Read
    @GetMapping("/courses/{id}")
    public CourseDTO getCourseById(@PathVariable UUID id) throws NotFoundException {
        return courseService.getCourseById(id);
    }

    @GetMapping("/courses")
    public List<CourseDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    // Update
    @PutMapping("/courses/{id}")
    public CourseDTO updateCourse(@PathVariable UUID id, @RequestBody CourseDTO courseDTO) throws NotFoundException {
        courseDTO.setId(id);
        return courseService.updateCourse(courseDTO);
    }

    // Delete
    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable UUID id) throws NotFoundException {
        courseService.deleteCourse(id);
    }

    // JOINS
    @GetMapping("/courses/names-dates-students")
    public List<CourseNameStartDateAndStudents> getCourseNameStartDateAndStudents() {
        return courseService.getCourseNameStartDateAndStudents();
    }

    @GetMapping("/courses/middle-level")
    public List<CourseOfLevelAndStudents> getCoursesOfLevelAndStudents() {
        return courseService.getCoursesOfLevelAndStudents();
    }
}
