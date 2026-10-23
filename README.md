# Student Management API

A Student Management REST API built using Spring Boot.

I created this project while practicing CRUD operations, DTOs, validation, global exception handling, structured API responses, Spring Data JPA, Servlet Filters, and Spring MVC Interceptors.

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
- API key authentication using AuthenticationFilter
- Role-based authorization using AuthorizationFilter
- USER and ADMIN roles
- Admin-only hard delete and soft delete
- Filter ordering using @Order
- Request logging using LoggingInterceptor
- Request execution time measurement using PerformanceInterceptor
- Spring MVC Interceptor registration using WebMvcConfigurer
- Spring AOP and custom annotation

## API Endpoints

| Method | Endpoint | Description | Access |
|---|---|---|---|
| POST | `/api/students` | Create a student | USER, ADMIN |
| GET | `/api/students/{id}` | Get student by ID | USER, ADMIN |
| GET | `/api/students` | Get all students | USER, ADMIN |
| PUT | `/api/students/{id}` | Update student | USER, ADMIN |
| DELETE | `/api/students/{id}` | Hard delete student | ADMIN only |
| PATCH | `/api/students/delete-soft/{id}` | Soft delete student | ADMIN only |

## Technologies Used

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Postman
- Jakarta Servlet API

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


## Filters

This project uses Servlet Filters for API key authentication and role-based authorization.

### AuthenticationFilter

The authentication filter checks the `x-api-key` request header and assigns a role based on the validated API key.

For local testing:

| API Key | Role |
|---|---|
| `user-12345` | USER |
| `admin-67890` | ADMIN |

These API keys are for demonstration purposes only.

The authenticated role is stored using:

`request.setAttribute("authenticatedRole", role)`

If the API key is missing or invalid, the request is stopped and the API returns:

```json
{
  "success": false,
  "message": "Invalid or missing api key"
}
```

HTTP Status: `401 Unauthorized`

### AuthorizationFilter

The authorization filter retrieves the authenticated role using `request.getAttribute()`.

- USER can create, retrieve, and update students.
- ADMIN can perform all operations.
- Only ADMIN can hard delete or soft delete students.

If a USER attempts a delete operation, the API returns:

```json
{
  "success": false,
  "message": "Access denied. ADMIN role required."
}
```

HTTP Status: `403 Forbidden`

Both Filters are ordered using `@Order`:

1. `AuthenticationFilter` — `@Order(1)`
2. `AuthorizationFilter` — `@Order(2)`



## Spring MVC Interceptors

This project uses Spring MVC Interceptors for request logging and performance monitoring.

Both Interceptors implement `HandlerInterceptor` and are registered using `WebMvcConfigurer` in `WebConfig`.

### LoggingInterceptor

The logging interceptor records:

- HTTP method
- Request URI
- Client IP address
- Authenticated user role
- Controller class name
- Controller method name
- HTTP response status
- Unresolved exception details, if available

It uses `preHandle()` to log incoming requests and `afterCompletion()` to log response status and exception details.

### PerformanceInterceptor

The performance interceptor measures request execution time using `System.nanoTime()`.

- `preHandle()` records the starting time.
- `afterCompletion()` calculates the elapsed time.
- Execution time is displayed in milliseconds.

### Interceptor Ordering

Both Interceptors are registered in `WebConfig` with the following order:

1. `LoggingInterceptor` — order 1
2. `PerformanceInterceptor` — order 2

Both are configured for `/api/**` endpoints.

The `preHandle()` methods execute in registration order, while `afterCompletion()` executes in reverse order.

Example console output:

```text
-------- Incoming request --------
HTTP method: GET
Request URI: /api/students/10
Client IP: 0:0:0:0:0:0:0:1
Authenticated role: USER
Controller: com.faraz.studentmanagementapi.controller.StudentController
Method: get
Request execution time: 15.42ms
Response status: 200
```

Requests rejected by the authentication or authorization Filters do not reach these Interceptors.


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

## Spring AOP

This project uses Spring AOP to measure the execution time of selected service methods through a custom annotation.

### `@TrackExecutionTime`

A custom annotation is used to mark service methods whose execution time should be measured.

The annotation supports:

- `operation`: A descriptive name for the operation.
- `warnAfter`: The execution-time threshold in milliseconds for logging a slow-operation warning.

### `ExecutionTimeAspect`

The aspect uses `@Around` advice with `ProceedingJoinPoint` to:

- Execute the original service method using `proceed()`.
- Measure execution time using `System.nanoTime()`.
- Display the elapsed time in milliseconds.
- Display a slow-operation warning when the configured threshold is reached.
- Preserve the original return value and allow exceptions to propagate.

The annotation is applied to the following service operations:

- Create student
- Get student by ID
- Get all students
- Update student
- Soft delete student
- Hard delete student

This keeps execution-time measurement separate from the business logic in the service layer.

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
- Servlet Filters
- `FilterChain`
- Filter ordering using `@Order`
- Reading HTTP request headers
- API key authentication
- Role-based authorization
- HTTP status codes `401 Unauthorized` and `403 Forbidden`
- Sharing data between Filters and Interceptors using request attributes
- Stopping or continuing requests using Filters
- Spring MVC `HandlerInterceptor`
- `preHandle()` and `afterCompletion()`
- Identifying controller methods using `HandlerMethod`
- Request logging using Interceptors
- Request execution time measurement using `System.nanoTime()`
- Registering Interceptors using `WebMvcConfigurer`
- Interceptor ordering and path patterns
- Spring AOP
- Custom annotations using `@Target` and `@Retention`
- `@Around` advice
- `ProceedingJoinPoint` and `proceed()`
- Service method execution-time measurement using `System.nanoTime()`
- Configurable slow-operation warning thresholds
- Preserving method return values and exception propagation in AOP advice