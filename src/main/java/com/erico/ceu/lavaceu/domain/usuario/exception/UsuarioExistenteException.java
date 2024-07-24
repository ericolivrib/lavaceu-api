package com.erico.ceu.lavaceu.domain.usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Usuário já existente")
public class UsuarioExistenteException extends RuntimeException {

    public UsuarioExistenteException() {
        super("Usuário já existente");
    }

}
