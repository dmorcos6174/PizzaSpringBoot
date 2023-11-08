package com.example.pizzaspringboot.center_mgmt.service;

import com.example.pizzaspringboot.caching.RedisService;
import com.example.pizzaspringboot.center_mgmt.dto.InstructorDTO;
import com.example.pizzaspringboot.center_mgmt.dto.InstructorAndCourses;
import com.example.pizzaspringboot.center_mgmt.dto.InstructorAndStudents;
import com.example.pizzaspringboot.center_mgmt.entity.Instructor;
import com.example.pizzaspringboot.center_mgmt.exception.AlreadyExistsException;
import com.example.pizzaspringboot.center_mgmt.exception.NotFoundException;
import com.example.pizzaspringboot.center_mgmt.repository.InstructorRepo;
import jakarta.persistence.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.pizzaspringboot.center_mgmt.mapper.EntityMapper.mapDTOToInstructor;
import static com.example.pizzaspringboot.center_mgmt.mapper.EntityMapper.mapInstructorToDTO;

@Service
public class InstructorService {

    Logger logger = LoggerFactory.getLogger(InstructorService.class);

    private final InstructorRepo instructorRepo;

    private final InstructorValidation instructorValidation;

    private final RedisService redisService;

    public InstructorService(InstructorRepo instructorRepo, InstructorValidation instructorValidation, RedisService redisService) {
        this.instructorRepo = instructorRepo;
        this.instructorValidation = instructorValidation;
        this.redisService = redisService;
    }

    // Create
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) throws AlreadyExistsException {
        if (!instructorRepo.findByName(instructorDTO.getFirstName(), instructorDTO.getLastName()).isEmpty()) {
            throw new AlreadyExistsException("Instructor with name: " + instructorDTO.getFirstName() + " " + instructorDTO.getLastName() + "already exists");
        }
        if (!isPhoneNumUnique(instructorDTO))
            throw new RuntimeException("Invalid Phone Number");
        if (!isEmailValid(instructorDTO.getEmail()))
            throw new RuntimeException("Invalid Email");
        Instructor savedInstructor = instructorRepo.save(mapDTOToInstructor(instructorDTO));
        return mapInstructorToDTO(savedInstructor);
    }

    // Read
    public InstructorDTO getInstructorById(UUID id) throws NotFoundException {
        var cachedInstructor = redisService.getValueFromRedis(id.toString());
        if (cachedInstructor.isPresent()) {
            logger.info("Cache HIT");
            return mapInstructorToDTO((Instructor) cachedInstructor.get());
        }
        else {
            Optional<Instructor> instructorOptional = instructorRepo.findById(id);
            if (instructorOptional.isEmpty()) {
                throw new NotFoundException("No Instructor with such id exists");
            }
            redisService.setValueInRedis(id.toString(), instructorOptional.get());
            logger.info("Cache MISS");
            return mapInstructorToDTO(instructorOptional.get());
        }

    }

    public List<InstructorDTO> getAllInstructor() {
        List<Instructor> instructorList = instructorRepo.findAll();
        List<InstructorDTO> instructorDTOList = new ArrayList<>();
        for (Instructor i : instructorList) {
            instructorDTOList.add(mapInstructorToDTO(i));
        }
        return instructorDTOList;
    }

    // Update
    public InstructorDTO updateInstructor(InstructorDTO instructorDTO) throws NotFoundException {
        Optional<Instructor> instructorOptional = instructorRepo.findById(instructorDTO.getId());
        if (instructorOptional.isEmpty()) {
            throw new NotFoundException("No Instructor with such id exists");
        }
        if (!isPhoneNumUnique(instructorDTO))
            throw new RuntimeException("Invalid Phone Number");
        if (!isEmailValid(instructorDTO.getEmail()))
            throw new RuntimeException("Invalid Email");
        if (!isYoutubeChannelPresent(instructorDTO))
            throw new RuntimeException("No Youtube Channel");
        Instructor updatedInstructor = instructorRepo.save(mapDTOToInstructor(instructorDTO));

        redisService.setValueInRedis(updatedInstructor.getId().toString(), updatedInstructor);

        return mapInstructorToDTO(updatedInstructor);
    }

    // Delete
    public boolean deleteInstructor(UUID id) {
        Optional<Instructor> instructorOptional = instructorRepo.findById(id);
        if (instructorOptional.isEmpty()) {
            throw new NotFoundException("No Instructor with such id exists");
        }
        instructorRepo.deleteById(id);

        redisService.deleteKey(id.toString());

        return !instructorRepo.existsById(id);
    }

    // JOINS
    public List<InstructorAndCourses> getInstructorNamesAndCourses() {
        List<Instructor> instructorList = instructorRepo.findAll();
        List<InstructorAndCourses> instructorAndCoursesList = new ArrayList<>();
        for (Instructor i : instructorList) {
            InstructorAndCourses x = new InstructorAndCourses(i.getFirstName(), i.getLastName(), i.getCourses());
            instructorAndCoursesList.add(x);
        }
        return instructorAndCoursesList;
    }

    public List<InstructorAndStudents> getInstructorNamesAndStudents() {
        List<Tuple> tuples = instructorRepo.findInstructorNamesAndStudents();
        List<InstructorAndStudents> instructorAndStudentsList = new ArrayList<>();
        for (Tuple t : tuples) {
            InstructorAndStudents x = new InstructorAndStudents(t.get(0, String.class), t.get(1, String.class), t.get(2, String.class), t.get(3, String.class));
            instructorAndStudentsList.add(x);
        }
        return instructorAndStudentsList;
    }

    protected boolean isPhoneNumUnique(InstructorDTO instructorDTO) {
        System.out.println("isPhoneNumUnique");
        return instructorValidation.isPhoneNumUnique(instructorDTO);
    }

    protected boolean isEmailValid(String emailAddress) {
        System.out.println("isEmailValid");
        return instructorValidation.isEmailValid(emailAddress);
    }

    protected boolean isYoutubeChannelPresent(InstructorDTO instructorDTO) {
        System.out.println("isYoutubeChannelPresent");
        return instructorValidation.isYoutubeChannelPresent(instructorDTO);
    }
}
