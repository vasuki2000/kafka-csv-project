package com.kafka.CSV_consumer.Repository;

import com.kafka.CSV_consumer.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, String> {

}

