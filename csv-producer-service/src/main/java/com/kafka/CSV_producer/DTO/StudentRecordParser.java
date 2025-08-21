package com.kafka.CSV_producer.DTO;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class StudentRecordParser {
    public static List<StudentRecord> parse(InputStream in) throws IOException {
        // Creating StudentRecord List
        List<StudentRecord> out = new ArrayList<>();

        try (Reader reader = new InputStreamReader(in);
             CSVParser parser = CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()
                     .parse(reader)) {

            for (CSVRecord r : parser) {
                StudentRecord item = StudentRecord.builder()
                        .studentId(Integer.parseInt(r.get("studentId").trim()))
                        .firstName(r.get("firstName".trim()))
                        .lastName(r.get("lastName".trim()))
                        .age(Integer.parseInt(r.get("age").trim()))
                        .gender(r.get("gender".trim()))
                        .department(r.get("department".trim()))
                        .marks(Integer.parseInt(r.get("marks").trim()))
                        .email(r.get("email".trim()))
                        .phoneNumber(r.get("phoneNumber".trim()))
                        .city(r.get("city".trim()))
                        .build();

                out.add(item);
            }
        }
        return out;
    }

}
