package com.example.pizzaspringboot.center_mgmt.dto;

import com.example.pizzaspringboot.center_mgmt.entities.Course;
import com.example.pizzaspringboot.center_mgmt.entities.InstructorDetails;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class InstructorDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNum;
}
