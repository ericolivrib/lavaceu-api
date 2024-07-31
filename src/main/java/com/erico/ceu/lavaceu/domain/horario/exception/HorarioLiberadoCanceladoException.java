package com.erico.ceu.lavaceu.domain.horario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Horário disponível cancelado")
public class HorarioLiberadoCanceladoException extends RuntimeException {

    public HorarioLiberadoCanceladoException() {
        super("Horário disponível cancelado");
    }
}
