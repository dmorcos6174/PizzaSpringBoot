package com.example.pizzaspringboot.center_mgmt.entities;

import com.example.pizzaspringboot.center_mgmt.enums.CourseLevel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "course_level")
    @Enumerated(EnumType.STRING)
    private CourseLevel courseLevel;

    @Column(name = "is_started")
    private Boolean isStarted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    @JsonBackReference
    private Instructor instructor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "enrolled_course"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @JsonBackReference
    private Set<Student> students;

    public Course() {
    }

    public Course(UUID id, String name, Date startDate, Date endDate, CourseLevel courseLevel, Boolean isStarted) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseLevel = courseLevel;
        this.isStarted = isStarted;
    }

    public Course(UUID id, String name, Date startDate, Date endDate, CourseLevel courseLevel, Boolean isStarted, Instructor instructor) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseLevel = courseLevel;
        this.isStarted = isStarted;
        this.instructor = instructor;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
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
