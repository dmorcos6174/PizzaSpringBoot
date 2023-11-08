package com.example.pizzaspringboot.center_mgmt.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "instructor_details")
public class InstructorDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "hobbies")
    private String hobbies;

    @OneToOne(mappedBy = "instructorDetails")
    @JsonManagedReference
    private Instructor instructor;
}
