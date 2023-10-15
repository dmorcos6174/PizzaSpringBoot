package com.example.pizzaspringboot.center_mgmt.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "instructor_details")
public class InstructorDetails {

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

    public InstructorDetails() {}

    public InstructorDetails(UUID id, String youtubeChannel, String hobbies) {
        this.id = id;
        this.youtubeChannel = youtubeChannel;
        this.hobbies = hobbies;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "------------InstructorDetails------------" + '\n' +
                "id=" + id + '\n' +
                "youtubeChannel=" + youtubeChannel + '\n' +
                "hobbies=" + hobbies + '\n' +
                "------------------------";
    }
}
