package com.example.pizzaspringboot.center_mgmt.repository;

import com.example.pizzaspringboot.center_mgmt.entity.Student;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepo extends JpaRepository<Student, UUID> {
    @Query("SELECT s FROM Student s WHERE s.firstName LIKE ?1 AND s.lastName LIKE ?2")
    List<Student> findByName(String firstName, String lastName);

    @Query("SELECT s.firstName, s.lastName FROM Student s JOIN s.courses c WHERE c.courseLevel = Middle")
    List<Tuple> findStudentNamesInMiddleLevel();
}
