package com.kafka.CSV_consumer.ConsumerService;

import com.kafka.CSV_consumer.Entity.StudentEntity;
import com.kafka.CSV_consumer.Repository.StudentRepository;
import com.kafka.CSV_producer.DTO.StudentRecord;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class StudentDataConsumer {

    @Autowired
    private StudentRepository studentRepository;

    @KafkaListener(topics = "csv.ingest.v1", groupId = "csv-consumer-group")
    @Transactional
    public void consume(Map<String, Object> message) {
        try {
            // Create a new entity
            StudentEntity entity = new StudentEntity();

            // Map JSON fields from Map to Entity
            entity.setStudentId(((Number) message.get("studentId")).intValue());
            entity.setFirstName((String) message.get("firstName"));
            entity.setLastName((String) message.get("lastName"));
            entity.setAge(((Number) message.get("age")).intValue());
            entity.setGender((String) message.get("gender"));
            entity.setDepartment((String) message.get("department"));
            entity.setMarks(((Number) message.get("marks")).intValue());
            entity.setEmail((String) message.get("email"));
//            entity.setPhoneNumber((String) message.get("phoneNumber"));
            String phone = (String) message.get("phoneNumber");
            if (phone.length() > 50) {
                phone = phone.substring(0, 50);
            }
            entity.setPhoneNumber(phone);

            entity.setCity((String) message.get("city"));


            // Save to DB
            studentRepository.save(entity);

            System.out.println("Consumed and saved: " + message);

        } catch (Exception ex) {
            System.err.println("Error processing record: " + message + " error: " + ex.getMessage());
        }
    }
}
