package com.example.pizzaspringboot.center_mgmt.dto;

import com.example.pizzaspringboot.center_mgmt.entities.Course;
import com.example.pizzaspringboot.center_mgmt.enums.Gender;

import java.util.Set;
import java.util.UUID;

public class StudentDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;
    private String email;
    private String phoneNum;
    private Long natId;
    private Set<Course> courses;

    public StudentDTO() {}

    public StudentDTO(UUID id, String firstName, String lastName, Integer age, Gender gender, String email, String phoneNum, Long natId, Set<Course> courses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phoneNum = phoneNum;
        this.natId = natId;
        this.courses = courses;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public Long getNatId() {
        return natId;
    }

    public void setNatId(Long natId) {
        this.natId = natId;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "------------Student------------" + '\n' +
                "id=" + id + '\n' +
                "firstName=" + firstName + '\n' +
                "lastName=" + lastName + '\n' +
                "age=" + age + '\n' +
                "gender=" + gender + '\n' +
                "email=" + email + '\n' +
                "phoneNum=" + phoneNum + '\n' +
                "natId=" + natId + '\n' +
                "------------------------";
    }
}
