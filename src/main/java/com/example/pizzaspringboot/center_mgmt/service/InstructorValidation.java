package com.example.pizzaspringboot.center_mgmt.service;

import com.example.pizzaspringboot.center_mgmt.dto.InstructorDTO;
import com.example.pizzaspringboot.center_mgmt.entities.Instructor;
import com.example.pizzaspringboot.center_mgmt.entities.InstructorDetails;
import com.example.pizzaspringboot.center_mgmt.repository.InstructorRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class InstructorValidation {
    private final InstructorRepo instructorRepo;

    public InstructorValidation(InstructorRepo instructorRepo) {
        this.instructorRepo = instructorRepo;
    }

    public boolean isPhoneNumUnique(InstructorDTO instructorDTO) {
        String phoneNum = instructorDTO.getPhoneNum();
        List<Instructor> instructorList = instructorRepo.findInstructorByPhoneNum(instructorDTO.getPhoneNum());
        return instructorList.isEmpty();
    }

    public boolean isEmailValid(String emailAddress) {
        return Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$")
                .matcher(emailAddress)
                .matches();
    }

    public boolean isYoutubeChannelPresent(InstructorDTO instructorDTO) {
        Instructor instructor = instructorRepo.findById(instructorDTO.getId()).get();
        InstructorDetails instructorDetails = instructor.getInstructorDetails();
        if (instructorDetails == null)
            return true;

        String youtubeChannel = instructorDetails.getYoutubeChannel();
        return youtubeChannel != null;
    }
}
