package in.exceptionHandeling.exceptionHandelingDemo.dto;

import java.time.LocalDateTime;

public class ExceptionResponseDTO {
    private LocalDateTime timeStamp;
    private boolean success;
    private int statusCode;
    private String error;
    private String message;
    private String path;


    public ExceptionResponseDTO
            (LocalDateTime timeStamp,
             boolean success,
             int statusCode,
             String error,
             String message,
             String path) {
        this.timeStamp = timeStamp;
        this.success = success;
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
