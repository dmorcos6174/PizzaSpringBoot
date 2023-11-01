package com.example.pizzaspringboot.center_mgmt.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CourseNameStartDateAndStudents {
    private String courseName;
    private LocalDateTime courseDate;
//    private String sFirstName;
//    private String sLastName;
    List<String> studentNames;
}
