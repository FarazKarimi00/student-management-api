package in.exceptionHandeling.exceptionHandelingDemo.controller;

import in.exceptionHandeling.exceptionHandelingDemo.dto.*;
import in.exceptionHandeling.exceptionHandelingDemo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CreateStudentResponseDTO> create
    (@Valid @RequestBody CreateStudentRequestDTO studentRequestDTO) {

        CreateStudentResponseDTO studentResp =
                studentService.createStudent(studentRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentResp);
    }

    // GET
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> get(@PathVariable Long id) {
        StudentResponseDTO studentResp = studentService.getStudent(id);

        return ResponseEntity.ok(studentResp);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<UpdateStudentResponseDTO> update
    (@PathVariable Long id, @Valid @RequestBody UpdateStudentRequestDTO studentRequestDTO) {

        UpdateStudentResponseDTO studentResp =
                studentService.updateStudent(id, studentRequestDTO);

        return ResponseEntity.ok(studentResp);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // SOFT DELETE
    @PatchMapping("/delete-soft/{id}")
    public ResponseEntity<String> softDelete(@PathVariable Long id) {
        studentService.deleteStudentSoftly(id);
        return ResponseEntity.noContent().build();
    }
}


