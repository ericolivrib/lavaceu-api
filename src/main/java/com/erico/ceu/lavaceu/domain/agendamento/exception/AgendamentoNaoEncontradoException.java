package com.erico.ceu.lavaceu.domain.agendamento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Agendamento não encontrado")
public class AgendamentoNaoEncontradoException extends RuntimeException {

    public AgendamentoNaoEncontradoException() {
        super("Agendamento não encontrado");
    }

}
