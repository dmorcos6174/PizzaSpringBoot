package com.example.pizzaspringboot.center_mgmt.entities;

import com.example.pizzaspringboot.center_mgmt.enums.Gender;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "nat_id")
    private Long natId;

    @ManyToMany
    @JoinTable(name = "student_course",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "enrolled_course")})
    private Set<Course> courses;

    public Student(UUID id, String firstName, String lastName, Integer age, Gender gender, String email, String phoneNum, Long natId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phoneNum = phoneNum;
        this.natId = natId;
    }

    public Student() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
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
