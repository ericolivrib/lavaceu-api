package com.erico.ceu.lavaceu.domain.horario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Horário já existente")
public class HorarioJaExistenteException extends RuntimeException {

    public HorarioJaExistenteException() {
        super("Horário já existente");
    }

}
