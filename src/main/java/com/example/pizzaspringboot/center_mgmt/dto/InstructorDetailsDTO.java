package com.example.pizzaspringboot.center_mgmt.dto;

import com.example.pizzaspringboot.center_mgmt.entities.Instructor;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class InstructorDetailsDTO {
    private UUID id;
    private String youtubeChannel;
    private String hobbies;
}
