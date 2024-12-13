package br.com.erico.lavanderia.infra.handler;

import br.com.erico.lavanderia.dto.FieldErrorDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FieldErrorDto>> handleFieldErrors(MethodArgumentNotValidException e) {
        List<FieldErrorDto> errors = e.getFieldErrors()
                .stream()
                .map(FieldErrorDto::new)
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<FieldErrorDto>> handleConstraintViolation(ConstraintViolationException e) {
        List<FieldErrorDto> errors = e.getConstraintViolations()
                .stream()
                .map(FieldErrorDto::new)
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Void> handleNotFoundError() {
        return ResponseEntity.notFound().build();
    }
}
