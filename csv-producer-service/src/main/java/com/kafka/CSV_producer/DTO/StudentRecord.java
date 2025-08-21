package com.kafka.CSV_producer.DTO;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRecord {
    @NotNull
    private int studentId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private int age;
    @NotNull
    private String gender;
    @NotNull
    private String department;
    @NotNull
    private int marks;
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String city;
}
