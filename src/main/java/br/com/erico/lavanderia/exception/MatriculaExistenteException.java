package br.com.erico.lavanderia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Número de matrícula já existente")
public class MatriculaExistenteException extends RuntimeException {

    public MatriculaExistenteException() {
        super("Número de matrícula já existente");
    }

}
