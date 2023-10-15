package com.example.pizzaspringboot.center_mgmt.dto;

import com.example.pizzaspringboot.center_mgmt.entities.Instructor;

import java.util.UUID;

public class InstructorDetailsDTO {
    private UUID id;
    private String youtubeChannel;
    private String hobbies;
    private Instructor instructor;

    public InstructorDetailsDTO() {}

    public InstructorDetailsDTO(UUID id, String youtubeChannel, String hobbies, Instructor instructor) {
        this.id = id;
        this.youtubeChannel = youtubeChannel;
        this.hobbies = hobbies;
        this.instructor = instructor;
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

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
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
