package com.kafka.CSV_producer.Controller;

import com.opencsv.CSVReader;
import org.springframework.ui.Model;
import com.kafka.CSV_producer.DTO.StudentRecord;
import com.kafka.CSV_producer.DTO.StudentRecordParser;
import com.kafka.CSV_producer.Producer.ProducerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
public class StudentFileUpload {

    private final ProducerService producer;
    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    public StudentFileUpload(ProducerService producer) {
        this.producer = producer;
    }


    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
        List<StudentRecord> rows = StudentRecordParser.parse(file.getInputStream());
        CompletableFuture<?>[] futures = rows.stream()
                .map(r -> producer.send(topic, r))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
        return ResponseEntity.accepted().body("Published " + rows.size() + " records to topic " + topic);
    }


    @PostMapping("/uploadFile")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] values;
            while ((values = reader.readNext()) != null) {
                // assuming CSV format: id,name,email
                StudentRecord record = new StudentRecord(
                        Integer.parseInt(values[0].trim()),
                        values[1].trim(),
                        values[2].trim(),
                        Integer.parseInt(values[3].trim()),
                        values[4].trim(),
                        values[5].trim(),
                        Integer.parseInt(values[6].trim()),
                        values[7].trim(),
                        values[8].trim(),
                        values[9].trim()

                );
                producer.send("csv-topic", record);
            }
            model.addAttribute("message", "File uploaded successfully and sent to Kafka.");
        } catch (Exception e) {
            model.addAttribute("message", "Error: " + e.getMessage());
        }
        return "upload";
    }

}
