package com.erico.ceu.lavaceu.domain.horario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Horário disponível não encontrado")
public class HorarioLiberadoNaoEncontradoException extends RuntimeException {

    public HorarioLiberadoNaoEncontradoException() {
        super("Horário disponível não encontrado");
    }
}
