package com.erico.ceu.lavaceu.domain.horario;

public enum DiaSemana {

    SEG("Segunda-feira"),
    TER("Terça-feira"),
    QUA("Quarta-feira"),
    QUI("Quinta-feira"),
    SEX("Sexta-feira"),
    SAB("Sábado");

    private final String dia;

    DiaSemana(String dia) {
        this.dia = dia;
    }

    public String getDia() {
        return dia;
    }

}
