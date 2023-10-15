package com.example.pizzaspringboot.center_mgmt.util;

import com.example.pizzaspringboot.center_mgmt.dto.CourseDTO;
import com.example.pizzaspringboot.center_mgmt.dto.InstructorDTO;
import com.example.pizzaspringboot.center_mgmt.dto.InstructorDetailsDTO;
import com.example.pizzaspringboot.center_mgmt.dto.StudentDTO;
import com.example.pizzaspringboot.center_mgmt.entities.Course;
import com.example.pizzaspringboot.center_mgmt.entities.Instructor;
import com.example.pizzaspringboot.center_mgmt.entities.InstructorDetails;
import com.example.pizzaspringboot.center_mgmt.entities.Student;

public class EntityMapper {

    public static CourseDTO mapCourseToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setStartDate(course.getStartDate());
        courseDTO.setEndDate(course.getEndDate());
        courseDTO.setCourseLevel(course.getCourseLevel());
        courseDTO.setStarted(course.getStarted());

        if (course.getInstructor() != null) {
            courseDTO.setInstructor(course.getInstructor());
        } else {
            courseDTO.setInstructor(null);
        }

        return courseDTO;
    }

    public static Course mapDTOToCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setId(courseDTO.getId());
        course.setName(courseDTO.getName());
        course.setStartDate(courseDTO.getStartDate());
        course.setEndDate(courseDTO.getEndDate());
        course.setCourseLevel(courseDTO.getCourseLevel());
        course.setStarted(courseDTO.getStarted());

        return course;
    }

    public static InstructorDTO mapInstructorToDTO(Instructor instructor) {
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setId(instructor.getId());
        instructorDTO.setFirstName(instructor.getFirstName());
        instructorDTO.setLastName(instructor.getLastName());
        instructorDTO.setEmail(instructor.getEmail());
        instructorDTO.setPhoneNum(instructor.getPhoneNum());
        instructorDTO.setCourses(instructor.getCourses());
        instructorDTO.setInstructorDetails(instructor.getInstructorDetails());

        return instructorDTO;
    }

    public static Instructor mapDTOToInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = new Instructor();
        instructor.setId(instructorDTO.getId());
        instructor.setFirstName(instructorDTO.getFirstName());
        instructor.setLastName(instructorDTO.getLastName());
        instructor.setEmail(instructorDTO.getEmail());
        instructor.setPhoneNum(instructorDTO.getPhoneNum());
        instructor.setCourses(instructorDTO.getCourses());
        instructor.setInstructorDetails(instructorDTO.getInstructorDetails());

        return instructor;
    }

    public static InstructorDetailsDTO mapInstructorDetailsToDTO(InstructorDetails instructorDetails) {
        InstructorDetailsDTO instructorDetailsDTO = new InstructorDetailsDTO();
        instructorDetailsDTO.setId(instructorDetails.getId());
        instructorDetailsDTO.setYoutubeChannel(instructorDetails.getYoutubeChannel());
        instructorDetailsDTO.setHobbies(instructorDetails.getHobbies());

        return instructorDetailsDTO;
    }

    public static InstructorDetails mapDTOToInstructorDetails(InstructorDetailsDTO instructorDetailsDTO) {
        InstructorDetails instructorDetails = new InstructorDetails();
        instructorDetails.setId(instructorDetailsDTO.getId());
        instructorDetails.setYoutubeChannel(instructorDetailsDTO.getYoutubeChannel());
        instructorDetails.setHobbies(instructorDetailsDTO.getHobbies());

        return instructorDetails;
    }

    public StudentDTO mapStudentToDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setAge(student.getAge());
        studentDTO.setGender(student.getGender());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setPhoneNum(student.getPhoneNum());
        studentDTO.setNatId(student.getNatId());
        studentDTO.setCourses(student.getCourses());

        return studentDTO;
    }

    public Student mapDTOToStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setId(studentDTO.getId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setAge(studentDTO.getAge());
        student.setGender(studentDTO.getGender());
        student.setEmail(studentDTO.getEmail());
        student.setPhoneNum(studentDTO.getPhoneNum());
        student.setNatId(studentDTO.getNatId());
        student.setCourses(studentDTO.getCourses());

        return student;
    }
}
