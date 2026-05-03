package com.alexlo.msvc_employee.shared.exception;

public class BadRequestException extends RuntimeException{
    private final String field;

    public BadRequestException(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
