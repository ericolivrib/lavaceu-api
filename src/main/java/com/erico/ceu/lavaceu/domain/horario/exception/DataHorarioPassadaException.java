package com.erico.ceu.lavaceu.domain.horario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Data anterior ao dia de hoje")
public class DataHorarioPassadaException extends RuntimeException {

    public DataHorarioPassadaException() {
        super("Data anterior ao dia de hoje");
    }

}
