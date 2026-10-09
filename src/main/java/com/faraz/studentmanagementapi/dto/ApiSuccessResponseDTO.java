package com.faraz.studentmanagementapi.dto;

import java.time.LocalDateTime;

public class ApiSuccessResponseDTO<T> {

    private LocalDateTime timestamp;
    private boolean success;
    private String message;
    private T data;

    public ApiSuccessResponseDTO(LocalDateTime timestamp,
                                 boolean success,
                                 String message,
                                 T data) {
        this.timestamp = timestamp;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
