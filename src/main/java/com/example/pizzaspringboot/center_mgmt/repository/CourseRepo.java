package com.example.pizzaspringboot.center_mgmt.repository;

import com.example.pizzaspringboot.center_mgmt.entities.Course;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepo extends JpaRepository<Course, UUID> {
    @Query("SELECT c FROM Course c WHERE c.name LIKE ?1")
    List<Course> findByName(String name);

    @Query("SELECT c.name, c.startDate, s.firstName, s.lastName FROM Course c JOIN c.students s")
//    @Query("SELECT s.firstName, s.lastName, c.name, c.startDate FROM Student s JOIN s.courses c")
    List<Tuple> findCourseNameStartDateAndStudents();
}
