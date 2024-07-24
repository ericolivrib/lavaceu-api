package com.erico.ceu.lavaceu.domain.lavadora.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Máquina de lavar não encontrada")
public class LavadoraNaoEncontradaException extends RuntimeException {

    public LavadoraNaoEncontradaException() {
        super("Máquina de lavar não encontrada");
    }
}
