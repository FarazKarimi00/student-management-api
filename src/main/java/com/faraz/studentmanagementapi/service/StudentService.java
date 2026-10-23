package com.faraz.studentmanagementapi.service;

import com.faraz.studentmanagementapi.aop.annotation.TrackExecutionTime;
import com.faraz.studentmanagementapi.dto.*;
import com.faraz.studentmanagementapi.entity.Student;
import com.faraz.studentmanagementapi.exception.DuplicateResourceException;
import com.faraz.studentmanagementapi.exception.ResourceNotFoundException;
import com.faraz.studentmanagementapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // CREATE
    @TrackExecutionTime(operation = "CREATE_STUDENT")
    public CreateStudentResponseDTO createStudent
    (CreateStudentRequestDTO studentRequestDTO) {

        Student student = mapToEntity(studentRequestDTO);

        if(emailExists(student.getEmail())) {
            throw new DuplicateResourceException
                    ("Student with email " + student.getEmail() + " already exists");
        }

        Student studentResp = studentRepository.save(student);
        return mapToCreateStudentResponseDTO(studentResp);
    }

    // GET
    @TrackExecutionTime(operation = "GET_STUDENT")
    public StudentResponseDTO getStudent(Long id) {
//        Optional<Student> studentResp = studentRepository.findByIdAndDeletedIsFalse(id);

//        if(studentResp.isPresent()) {
//            return mapToCreateStudentResponseDTO(studentResp.get());
//        }
//        return null;
//        return mapToCreateStudentResponseDTO(studentResp.get());

//        Student studentResp = studentRepository
//                .findByIdAndDeletedIsFalse(id)
//                .orElse(null);
//
//        return mapToCreateStudentResponseDTO(studentResp);

        Student studentResp = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException
                                ("Student with id " + id + " not found."));
        return mapToStudentResponseDTO(studentResp);
    }

    // GET ALL
    @TrackExecutionTime(operation = "GET_ALL_STUDENTS")
    public List<StudentResponseDTO> getAllStudents() {
        List<Student> studentList = studentRepository.findByDeletedIsFalse();
        return studentList.stream()
                .map(this::mapToStudentResponseDTO)
                .toList();
    }

    // UPDATE
    @TrackExecutionTime(operation = "UPDATE_STUDENT")
    public UpdateStudentResponseDTO updateStudent
    (Long id, UpdateStudentRequestDTO studentRequestDTO) {

        Student existingStudent = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Student with id " + id + " not found."));


        existingStudent.setName(studentRequestDTO.getName());
        existingStudent.setAge(studentRequestDTO.getAge());
        existingStudent.setRollNo(studentRequestDTO.getRollNo());
        existingStudent.setSubject(studentRequestDTO.getSubject());
        existingStudent.setUpdatedAt(LocalDateTime.now());

        Student savedStudent = studentRepository.save(existingStudent);
        return mapToUpdateStudentResponseDTO(savedStudent);
    }

    // DELETE
    @TrackExecutionTime(operation = "DELETE_STUDENT")
    public void deleteStudent(Long id) {

        Student studentToDelete = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Student with id " + id + " not found."));

        studentRepository.delete(studentToDelete);
    }

    // SOFT DELETE
    @TrackExecutionTime(operation = "SOFT_DELETE_STUDENT")
    public void deleteStudentSoftly(Long id) {
        Student studentToDelete = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Student with id " + id + " not found"));


        studentToDelete.setDeleted(true);
        studentToDelete.setUpdatedAt(LocalDateTime.now());
        studentRepository.save(studentToDelete);
    }

    // Mapping methods
    private Student mapToEntity
    (CreateStudentRequestDTO studentRequestDTO) {

        Student student = new Student();
        student.setName(studentRequestDTO.getName());
        student.setAge(studentRequestDTO.getAge());
        student.setEmail(studentRequestDTO.getEmail());
        student.setRollNo(studentRequestDTO.getRollNo());
        student.setSubject(studentRequestDTO.getSubject());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        student.setDeleted(false);

        return student;
    }

    private CreateStudentResponseDTO mapToCreateStudentResponseDTO
            (Student student) {

        CreateStudentResponseDTO responseDTO =
                new CreateStudentResponseDTO();
        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setRollNo(student.getRollNo());
        responseDTO.setSubject(student.getSubject());
        responseDTO.setCreatedAt(student.getCreatedAt());
        responseDTO.setUpdatedAt(student.getUpdatedAt());
        return responseDTO;
    }

    private StudentResponseDTO mapToStudentResponseDTO(Student student) {
        StudentResponseDTO responseDTO = new StudentResponseDTO();
        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setRollNo(student.getRollNo());
        responseDTO.setSubject(student.getSubject());
        responseDTO.setCreatedAt(student.getCreatedAt());
        responseDTO.setUpdatedAt(student.getUpdatedAt());
        return responseDTO;
    }

    private UpdateStudentResponseDTO mapToUpdateStudentResponseDTO
            (Student student) {

        UpdateStudentResponseDTO responseDTO =
                new UpdateStudentResponseDTO();
        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setAge(student.getAge());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setRollNo(student.getRollNo());
        responseDTO.setSubject(student.getSubject());
        responseDTO.setUpdatedAt(student.getUpdatedAt());
        return responseDTO;
    }

    // Check methods
    private boolean emailExists(String email) {
        return studentRepository.existsByEmail(email);
    }
}
