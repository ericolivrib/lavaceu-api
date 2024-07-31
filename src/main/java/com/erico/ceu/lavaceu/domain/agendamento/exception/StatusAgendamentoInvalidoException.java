package com.erico.ceu.lavaceu.domain.agendamento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Status de agendamento inválido para essa operação")
public class StatusAgendamentoInvalidoException extends RuntimeException {

    public StatusAgendamentoInvalidoException() {
        super("Status de agendamento inválido para essa operação");
    }

}
