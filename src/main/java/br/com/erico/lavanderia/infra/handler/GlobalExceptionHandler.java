package br.com.erico.lavanderia.infra.handler;

import br.com.erico.lavanderia.dto.FieldErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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
}
