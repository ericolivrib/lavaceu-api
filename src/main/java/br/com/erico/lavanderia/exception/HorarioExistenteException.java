package br.com.erico.lavanderia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Horário com hora e período já presentes")
public class HorarioExistenteException extends RuntimeException {

    public HorarioExistenteException() {
        super("Horário com hora e período já presentes");
    }
}
