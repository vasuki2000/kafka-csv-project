# This is a Spring Boot Project to parse your CSV file data to Database. 


# Data-Base setup: 
# DB Name: student :  CREATE DATABASE student;
# Table creation : 
CREATE TABLE STUDENT_TABLE (
    StudentId INT NOT NULL PRIMARY KEY,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    Age INT,
    Gender VARCHAR(50),
    Department VARCHAR(100),
    Marks INT,
    Email VARCHAR(255),
    PhoneNumber VARCHAR(50),
    City VARCHAR(100)
);

# Note: Run the application only after setting the DB

# Producer API endpoints: http://localhost:8080/api/upload & http://localhost:8080/api/uploadFile
# Consumer API endpoints: http://localhost:8081/api/students
