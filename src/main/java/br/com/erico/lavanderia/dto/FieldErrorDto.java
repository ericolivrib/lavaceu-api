package br.com.erico.lavanderia.dto;

import org.springframework.validation.FieldError;

public record FieldErrorDto(String field, String message) {

    public FieldErrorDto(FieldError fieldError) {
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
