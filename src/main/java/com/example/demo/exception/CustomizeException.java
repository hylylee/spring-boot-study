package com.example.demo.exception;

public class CustomizeException extends RuntimeException {
    private String message;

    public CustomizeException(String message) {
        this.message = message;
    }

    public CustomizeException(ICustomizeErrorCode customizeErrorCode) {
        this.message = customizeErrorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
