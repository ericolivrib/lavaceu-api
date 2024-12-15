package br.com.erico.lavanderia.model.horario;

import br.com.erico.lavanderia.exception.HorarioInvalidoException;

import java.time.LocalTime;

public enum PeriodoDia {
    MANHA, TARDE, NOITE;
    
    public static PeriodoDia getInstance(LocalTime horario) {
        if (horario.isAfter(LocalTime.of(6, 0)) && horario.isBefore(LocalTime.of(12, 0))) {
            return PeriodoDia.MANHA;
        } else if (horario.isAfter(LocalTime.of(12, 0)) && horario.isBefore(LocalTime.of(18, 0))) {
            return PeriodoDia.TARDE;
        } else if (horario.isAfter(LocalTime.of(18, 0)) && horario.isBefore(LocalTime.of(0, 0))) {
            return PeriodoDia.NOITE;
        } else {
            throw new HorarioInvalidoException();
        }
    }
}
