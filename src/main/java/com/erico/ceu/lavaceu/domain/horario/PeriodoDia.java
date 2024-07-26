package com.erico.ceu.lavaceu.domain.horario;

import java.time.LocalTime;

public enum PeriodoDia {

    MANHA(LocalTime.of(6,0), LocalTime.of(11, 59, 59)),
    TARDE(LocalTime.of(12,0), LocalTime.of(17, 59, 59)),
    NOITE(LocalTime.of(18,0), LocalTime.of(23, 59, 59));

    private final LocalTime horaInicial;
    private final LocalTime horaFinal;

    PeriodoDia(LocalTime horaInicial, LocalTime horaFinal) {
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
    }

    public LocalTime getHoraInicial() {
        return horaInicial;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

}
