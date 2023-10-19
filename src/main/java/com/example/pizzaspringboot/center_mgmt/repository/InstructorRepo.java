package com.example.pizzaspringboot.center_mgmt.repository;

import com.example.pizzaspringboot.center_mgmt.dto.InstructorNameAndCourses;
import com.example.pizzaspringboot.center_mgmt.dto.InstructorNameAndStudents;
import com.example.pizzaspringboot.center_mgmt.entities.Instructor;
import com.example.pizzaspringboot.center_mgmt.entities.Student;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor, UUID> {
    @Query("SELECT i FROM Instructor i WHERE i.firstName LIKE ?1 AND i.lastName LIKE ?2")
    List<Instructor> findByName(String firstName, String lastName);

    @Query("SELECT i.firstName, i.lastName, s.firstName, s.lastName FROM Instructor i JOIN i.courses c JOIN c.students s")
    List<Tuple> findInstructorNamesAndStudents();
}
