package com.faraz.studentmanagementapi.dto;

import jakarta.validation.constraints.*;

public class CreateStudentRequestDTO {

    @NotBlank(message = "Name cannot be empty, null or blank")
    @Size(min = 2, max = 50, message = "Name size must in between 2-50 characters")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Student must be at least 18 years old")
    private Integer age;

    @NotBlank(message = "Email is required")
    @Email(message = "Student email must be valid")
    private String email;

    @NotNull(message = "Roll number is required")
    private Integer rollNo;

    @NotBlank(message = "Subject is required")
    private String subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
