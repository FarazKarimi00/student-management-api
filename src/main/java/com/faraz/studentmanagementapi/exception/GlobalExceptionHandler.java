package com.faraz.studentmanagementapi.exception;

import com.faraz.studentmanagementapi.dto.ExceptionResponseDTO;
import com.faraz.studentmanagementapi.dto.ValidationExceptionResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handleResourceNotFoundException
            (ResourceNotFoundException ex, HttpServletRequest request) {

        ExceptionResponseDTO exceptionResp = new ExceptionResponseDTO(
                LocalDateTime.now(),
                false,
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResp);
    }


    // Handle DuplicateResourceException
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionResponseDTO> duplicateResourceException
            (DuplicateResourceException ex, HttpServletRequest request) {

        ExceptionResponseDTO exceptionResp = new ExceptionResponseDTO(
                LocalDateTime.now(),
                false,
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionResp);
    }


    // Handle MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponseDTO> methodArgumentNotValidException
            (MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> fieldErrors
                        .put(error.getField(), error.getDefaultMessage()));

        ValidationExceptionResponseDTO exceptionResp = new ValidationExceptionResponseDTO(
                LocalDateTime.now(),
                false,
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validation Failed",
                request.getRequestURI(),
                fieldErrors
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionResp);
    }

    // Handle RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponseDTO> handleRuntimeException
            (RuntimeException ex, HttpServletRequest request) {

        ExceptionResponseDTO exceptionResp = new ExceptionResponseDTO(
                LocalDateTime.now(),
                false,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "An unexpected server error occurred",
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResp);
    }

    // Handle generic Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleException
            (Exception ex, HttpServletRequest request) {

        ExceptionResponseDTO exceptionResp = new ExceptionResponseDTO(
                LocalDateTime.now(),
                false,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "Something went wrong. Please try again later",
                request.getRequestURI()

        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResp);
    }
}
