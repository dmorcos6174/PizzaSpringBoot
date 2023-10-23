package com.example.pizzaspringboot.center_mgmt.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class InstructorNameAndStudents {
    private String iFirstName;
    private String iLastName;
    private String sFirstName;
    private String sLastName;
}
