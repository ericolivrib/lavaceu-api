package br.com.erico.lavanderia.dto;

import jakarta.validation.ConstraintViolation;
import org.springframework.validation.FieldError;

public record FieldErrorDto(String field, String message) {

    public FieldErrorDto(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }

    public FieldErrorDto(ConstraintViolation<?> violation) {
        this(violation.getPropertyPath().toString(), violation.getMessage());
    }
}
