package com.erico.ceu.lavaceu.domain.horario;

public class PeriodoDiaInvalidoException extends RuntimeException {

    public PeriodoDiaInvalidoException() {
        super("Período do dia inválido");
    }

}
