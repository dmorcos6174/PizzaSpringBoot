package com.example.pizzaspringboot.center_mgmt.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CourseOfLevelAndStudents {
    private String courseName;
    private List<String> studentNames;
}
