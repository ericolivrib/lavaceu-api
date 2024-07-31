package com.erico.ceu.lavaceu.domain.horario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "Horário está presente em agendamentos")
public class HorarioEmUsoException extends RuntimeException {

    public HorarioEmUsoException() {
        super("Horário está presente em agendamentos");
    }
}
