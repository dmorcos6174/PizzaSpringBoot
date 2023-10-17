package com.example.pizzaspringboot.center_mgmt.dto;

import com.example.pizzaspringboot.center_mgmt.enums.Gender;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class StudentDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;
    private String email;
    private String phoneNum;
    private Long natId;
}
