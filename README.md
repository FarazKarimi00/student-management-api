# Student Management API

This is a simple Student Management REST API built using Spring Boot.

I created this project while practicing DTOs, validation and global exception handling in Spring Boot.

## Features

- Create student
- Get student by ID
- Get all students
- Update student
- Delete student
- Soft delete
- Request and response DTOs
- Validation using `@Valid`
- Global exception handling
- Custom exceptions
- MySQL database integration

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Postman

## Exception Handling

This project uses `@RestControllerAdvice` for global exception handling.

Handled cases include:

- Student not found
- Duplicate resource
- Validation errors
- Runtime exceptions
- General exceptions

## What I Practiced

While building this project, I practiced:

- DTOs
- Validation
- Manual DTO to Entity mapping
- Soft delete
- Custom exceptions
- Global exception handling
- HTTP status codes