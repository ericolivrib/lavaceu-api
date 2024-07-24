package com.erico.ceu.lavaceu.domain.horario.dto;

import com.erico.ceu.lavaceu.domain.horario.DiaSemana;
import com.erico.ceu.lavaceu.domain.horario.Horario;

import java.time.LocalTime;
import java.util.UUID;

public record CriarHorarioRequest(
        DiaSemana diaSemana,
        LocalTime hora
) {

    public Horario toHorarioEntity() {
        return new Horario(UUID.randomUUID(), diaSemana, Horario.Status.BLOQUEADO, hora);
    }

}
