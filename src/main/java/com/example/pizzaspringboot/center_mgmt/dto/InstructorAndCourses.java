package com.example.pizzaspringboot.center_mgmt.dto;

import com.example.pizzaspringboot.center_mgmt.entities.Course;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class InstructorAndCourses {
    private String firstName;
    private String lastName;
    private List<Course> courses;
}
