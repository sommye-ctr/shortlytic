package com.mtj.shortlytic.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handle(MethodArgumentNotValidException ex) {
        Map<String, String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            String field = ((FieldError) objectError).getField();
            String error = objectError.getDefaultMessage();
            resp.put(field, error);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handle(ConstraintViolationException ex) {
        Map<String, String> resp = new HashMap<>();
        ex.getConstraintViolations().forEach(objectError -> {
            String field = objectError.getPropertyPath().toString();
            String error = objectError.getMessage();
            resp.put(field, error);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<?, ?>> handle(ResourceNotFoundException ex) {
        HashMap<Object, Object> resp = new HashMap<>();
        resp.put("message", ex.getMessage());
        resp.put("status", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
    }
}
