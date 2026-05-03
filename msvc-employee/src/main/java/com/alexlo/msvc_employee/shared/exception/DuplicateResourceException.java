package com.alexlo.msvc_employee.shared.exception;

public class DuplicateResourceException extends RuntimeException {

    private final String field;

    public DuplicateResourceException(String message, String field) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
