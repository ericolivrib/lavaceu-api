package com.erico.ceu.lavaceu.domain.horario.dto;

import com.erico.ceu.lavaceu.domain.horario.DiaSemana;
import com.erico.ceu.lavaceu.domain.horario.Horario;
import com.erico.ceu.lavaceu.domain.horario.PeriodoDiaInvalidoException;

import java.time.LocalTime;
import java.util.UUID;

public record CriarHorarioRequest(DiaSemana diaSemana, LocalTime hora) {

    public Horario toHorarioEntity() throws PeriodoDiaInvalidoException {
        return new Horario(UUID.randomUUID(), diaSemana, hora);
    }

}
