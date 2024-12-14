package com.mtj.shortlytic.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final String field;
    private final long fieldId;

    public ResourceNotFoundException(String message, String field, long fieldId) {
        super(message);
        this.field = field;
        this.fieldId = fieldId;
    }

    public ResourceNotFoundException(String field, long fieldId) {
        super(String.format("Resource not found: %s with id %d", field, fieldId));
        this.field = field;
        this.fieldId = fieldId;
    }
}
