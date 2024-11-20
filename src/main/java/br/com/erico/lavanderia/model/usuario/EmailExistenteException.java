package br.com.erico.lavanderia.model.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "E-mail já está em uso")
public class EmailExistenteException extends RuntimeException {

    public EmailExistenteException() {
        super("E-mail já está em uso");
    }
}
