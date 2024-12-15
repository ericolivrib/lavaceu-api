package br.com.erico.lavanderia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Horário de agendamento deve estar entre 06 AM e 22 PM")
public class HorarioInvalidoException extends IllegalArgumentException {

    public HorarioInvalidoException() {
        super("Horário de agendamento deve estar entre 06 AM e 22 PM");
    }
}
