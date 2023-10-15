package com.example.pizzaspringboot.center_mgmt.repository;

import com.example.pizzaspringboot.center_mgmt.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor, UUID> {
}
