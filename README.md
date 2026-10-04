# Student Management API

A Student Management REST API built using Spring Boot.

I created this project while practicing CRUD operations, DTOs, validation, global exception handling, and structured API responses in Spring Boot.

## Features

- Create student
- Get student by ID
- Get all students
- Update student
- Hard delete student
- Soft delete student
- Request and response DTOs
- Validation using `@Valid`
- Global exception handling using `@RestControllerAdvice`
- Custom exceptions
- Structured success and error responses
- Field-level validation error responses
- Duplicate email handling
- Unique email constraint
- Proper HTTP status codes
- MySQL database integration

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/students` | Create a student |
| GET | `/api/students/{id}` | Get student by ID |
| GET | `/api/students` | Get all students |
| PUT | `/api/students/{id}` | Update student |
| DELETE | `/api/students/{id}` | Hard delete student |
| PATCH | `/api/students/soft-delete/{id}` | Soft delete student |

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Postman

## Exception Handling

This project uses `@RestControllerAdvice` for centralized exception handling.

Handled cases include:

- Resource not found
- Duplicate resource
- Validation errors
- Runtime exceptions
- General exceptions

Error responses are returned in a structured JSON format with details such as:

- Timestamp
- Success status
- HTTP status code
- Error
- Message
- Request path

## Success Responses

Successful responses are wrapped using a generic `ApiSuccessResponseDTO<T>`.

Example:

```json
{
  "timestamp": "2026-10-05T01:30:00",
  "success": true,
  "message": "Student retrieved successfully",
  "data": {
    "id": 1,
    "name": "Abc",
    "email": "abc@example.com"
  }
}
```

## What I Practiced

While building this project, I practiced:

- RESTful CRUD operations
- DTOs
- Validation using `@Valid`
- Manual DTO to Entity mapping
- Entity to DTO mapping
- Generic success response wrapper using `ApiSuccessResponseDTO<T>`
- Structured error responses
- Field-level validation error handling
- Custom exceptions
- Global exception handling using `@RestControllerAdvice`
- Proper HTTP status codes
- Duplicate email handling
- Soft delete
- Spring Data JPA derived query methods
- MySQL database integration
