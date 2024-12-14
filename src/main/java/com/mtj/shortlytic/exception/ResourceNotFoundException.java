package com.mtj.shortlytic.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final String field;
    private final String fieldId;

    public ResourceNotFoundException(String message, String field, String fieldId) {
        super(message);
        this.field = field;
        this.fieldId = fieldId;
    }

    public ResourceNotFoundException(String field, String fieldId) {
        super(String.format("Resource not found: %s with id %s", field, fieldId));
        this.field = field;
        this.fieldId = fieldId;
    }
}
