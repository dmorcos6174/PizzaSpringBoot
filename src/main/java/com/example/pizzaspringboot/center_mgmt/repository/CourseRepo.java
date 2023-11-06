package com.example.pizzaspringboot.center_mgmt.repository;

import com.example.pizzaspringboot.center_mgmt.dto.CourseOfLevelAndStudents;
import com.example.pizzaspringboot.center_mgmt.entity.Course;
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
    List<Tuple> findCourseNameStartDateAndStudents();

    @Query("""
SELECT
new com.example.pizzaspringboot.center_mgmt.dto.CourseOfLevelAndStudents(c.name, LISTAGG(s.firstName || ' ' || s.lastName, ', ') WITHIN GROUP (ORDER BY s.firstName))
FROM Course c
JOIN c.students s
WHERE c.courseLevel = 'Middle'
GROUP BY c.name, c.startDate
""")
    List<CourseOfLevelAndStudents> findCoursesOfLevelAndStudents();
}
