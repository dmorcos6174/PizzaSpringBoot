package com.example.pizzaspringboot.center_mgmt.dto;

import com.example.pizzaspringboot.center_mgmt.entities.Course;
import com.example.pizzaspringboot.center_mgmt.entities.InstructorDetails;

import java.util.List;
import java.util.UUID;

public class InstructorDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNum;
    private List<Course> courses;
    private InstructorDetails instructorDetails;

    public InstructorDTO() {}

    public InstructorDTO(UUID id, String firstName, String lastName, String email, String phoneNum, List<Course> courses, InstructorDetails instructorDetails) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.courses = courses;
        this.instructorDetails = instructorDetails;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public InstructorDetails getInstructorDetails() {
        return instructorDetails;
    }

    public void setInstructorDetails(InstructorDetails instructorDetails) {
        this.instructorDetails = instructorDetails;
    }

    @Override
    public String toString() {
        return "------------Instructor------------" + '\n' +
                "id=" + id + '\n' +
                "firstName=" + firstName + '\n' +
                "lastName=" + lastName + '\n' +
                "email=" + email + '\n' +
                "phoneNum=" + phoneNum + '\n' +
                "------------------------";
    }
}
