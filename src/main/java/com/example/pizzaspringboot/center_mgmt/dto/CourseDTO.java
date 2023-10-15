package com.example.pizzaspringboot.center_mgmt.dto;

import com.example.pizzaspringboot.center_mgmt.entities.Instructor;
import com.example.pizzaspringboot.center_mgmt.entities.Student;
import com.example.pizzaspringboot.center_mgmt.enums.CourseLevel;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class CourseDTO {

    private UUID id;
    private String name;
    private Date startDate;
    private Date endDate;
    private CourseLevel courseLevel;
    private Boolean isStarted;
    private Instructor instructor;
    private Set<Student> students;

    public CourseDTO() {}

    public CourseDTO(UUID id, String name, Date startDate, Date endDate, CourseLevel courseLevel, Boolean isStarted) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseLevel = courseLevel;
        this.isStarted = isStarted;
    }

    public CourseDTO(UUID id, String name, Date startDate, Date endDate, CourseLevel courseLevel, Boolean isStarted, Instructor instructor, Set<Student> students) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseLevel = courseLevel;
        this.isStarted = isStarted;
        this.instructor = instructor;
        this.students = students;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public CourseLevel getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(CourseLevel courseLevel) {
        this.courseLevel = courseLevel;
    }

    public Boolean getStarted() {
        return isStarted;
    }

    public void setStarted(Boolean started) {
        isStarted = started;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        if (instructor != null) {
            return "------------Course------------" + '\n' +
                    "id=" + id + '\n' +
                    "name=" + name + '\n' +
                    "startDate=" + startDate + '\n' +
                    "endDate=" + endDate + '\n' +
                    "courseLevel=" + courseLevel + '\n' +
                    "isStarted=" + isStarted + '\n' +
                    "instructor=" + instructor.getFirstName() + " " + instructor.getLastName() + '\n' +
                    "------------------------";
        } else {
            return "------------Course------------" + '\n' +
                    "id=" + id + '\n' +
                    "name=" + name + '\n' +
                    "startDate=" + startDate + '\n' +
                    "endDate=" + endDate + '\n' +
                    "courseLevel=" + courseLevel + '\n' +
                    "isStarted=" + isStarted + '\n' +
                    "instructor=" + "No Instructor Assigned" + '\n' +
                    "------------------------";
        }
    }
}
