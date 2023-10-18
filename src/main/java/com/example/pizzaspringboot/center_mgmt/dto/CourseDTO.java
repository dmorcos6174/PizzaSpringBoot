package com.example.pizzaspringboot.center_mgmt.dto;

import com.example.pizzaspringboot.center_mgmt.enums.CourseLevel;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CourseDTO {
    private UUID id;
    private String name;
    private Date startDate;
    private Date endDate;
    private CourseLevel courseLevel;
    private Boolean isStarted;
}
