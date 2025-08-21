package com.kafka.CSV_consumer.Controller;

import com.kafka.CSV_consumer.Entity.StudentEntity;
import com.kafka.CSV_producer.DTO.StudentRecord;
import com.kafka.CSV_consumer.Repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentDataController {

    private final StudentRepository studentRepository;

    public StudentDataController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

//    @GetMapping("/students")
//    public List<StudentRecord> getAllStudents() {
//        return studentRepository.findAll()
//                .stream()
//                .map(e -> new StudentRecord(
//                        e.getStudentId(),
//                        e.getFirstName(),
//                        e.getLastName(),
//                        e.getAge(),
//                        e.getGender(),
//                        e.getDepartment(),
//                        e.getMarks(),
//                        e.getEmail(),
//                        e.getPhoneNumber(),
//                        e.getCity()
//                ))
//                .toList();
//    }

    @GetMapping ("students")
    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }



}

