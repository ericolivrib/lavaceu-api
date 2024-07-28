package com.erico.ceu.lavaceu.domain.agendamento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Agendamento já existente")
public class AgendamentoJaExistente extends RuntimeException {

    public AgendamentoJaExistente() {
        super("Agendamento já existente");
    }
}
