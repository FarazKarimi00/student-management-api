package in.exceptionHandeling.exceptionHandelingDemo.controller;

import in.exceptionHandeling.exceptionHandelingDemo.dto.*;
import in.exceptionHandeling.exceptionHandelingDemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ApiSuccessResponseDTO<CreateStudentResponseDTO>> create
    (@Valid @RequestBody CreateStudentRequestDTO studentRequestDTO) {

        CreateStudentResponseDTO studentResponseDTO =
                studentService.createStudent(studentRequestDTO);

        ApiSuccessResponseDTO<CreateStudentResponseDTO> response =
                new ApiSuccessResponseDTO<>(
                        LocalDateTime.now(),
                        true,
                        "Student created Successfully",
                        studentResponseDTO
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // GET
    @GetMapping("/{id}")
    public ResponseEntity<ApiSuccessResponseDTO<StudentResponseDTO>> get
    (@PathVariable Long id) {

        StudentResponseDTO studentResp = studentService.getStudent(id);

        ApiSuccessResponseDTO<StudentResponseDTO> response =
                new ApiSuccessResponseDTO<>(
                        LocalDateTime.now(),
                        true,
                        "Student retrieved successfully",
                        studentResp
                );
        return ResponseEntity.ok(response);

    }

    // GET ALL
    @GetMapping
    public ResponseEntity<ApiSuccessResponseDTO<List<StudentResponseDTO>>> getAll() {
        List<StudentResponseDTO> studentList = studentService.getAllStudents();

        ApiSuccessResponseDTO<List<StudentResponseDTO>> response =
                new ApiSuccessResponseDTO<>(
                        LocalDateTime.now(),
                        true,
                        "Students retrieved successfully",
                        studentList
                );

        return ResponseEntity.ok(response);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiSuccessResponseDTO<UpdateStudentResponseDTO>> update
    (@PathVariable Long id, @Valid @RequestBody UpdateStudentRequestDTO studentRequestDTO) {

        UpdateStudentResponseDTO studentResp =
                studentService.updateStudent(id, studentRequestDTO);

        ApiSuccessResponseDTO<UpdateStudentResponseDTO> response =
                new ApiSuccessResponseDTO<>(
                        LocalDateTime.now(),
                        true,
                        "Student updated successfully",
                        studentResp
                );

        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // SOFT DELETE
    @PatchMapping("/delete-soft/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        studentService.deleteStudentSoftly(id);
        return ResponseEntity.noContent().build();
    }
}


