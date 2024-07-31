package com.erico.ceu.lavaceu.domain.horario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Horário já liberado para a data informada")
public class HorarioJaLiberadoException extends RuntimeException {

    public HorarioJaLiberadoException() {
        super("Horário já liberado para a data informada");
    }
}
