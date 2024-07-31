package com.erico.ceu.lavaceu.domain.horario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Horário ocupado por outro usuário")
public class HorarioLiberadoOcupadoException extends RuntimeException {

    public HorarioLiberadoOcupadoException() {
        super("Horário ocupado por outro usuário");
    }
}
