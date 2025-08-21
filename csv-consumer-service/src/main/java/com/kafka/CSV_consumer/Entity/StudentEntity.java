package com.kafka.CSV_consumer.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "STUDENT_TABLE")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class StudentEntity {

    @Id
    @Column(name= "StudentId")
    private int studentId;

    @Column(name= "FirstName")
    private String firstName;

    @Column(name= "LastName")
    private String lastName;

    @Column(name= "Age")
    private int age;

    @Column(name= "Gender")
    private String gender;

    @Column(name= "Department")
    private String department;

    @Column(name= "Marks")
    private int marks;

    @Column(name= "Email")
    private String email;

    @Column(name= "PhoneNumber", length = 50)
    private String phoneNumber;

    @Column(name= "City")
    private String city;

}
