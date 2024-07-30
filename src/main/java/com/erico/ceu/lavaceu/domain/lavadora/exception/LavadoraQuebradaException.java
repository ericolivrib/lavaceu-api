package com.erico.ceu.lavaceu.domain.lavadora.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "Lavadora quebrada")
public class LavadoraQuebradaException extends RuntimeException {

    public LavadoraQuebradaException() {
        super("Lavadora quebrada");
    }

}
