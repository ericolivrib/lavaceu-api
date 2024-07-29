package com.erico.ceu.lavaceu.domain.horario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Horário não encontrado")
public class HorarioNaoEncontradoException extends RuntimeException {

    public HorarioNaoEncontradoException() {
        super("Horário não encontrado");
    }
}
