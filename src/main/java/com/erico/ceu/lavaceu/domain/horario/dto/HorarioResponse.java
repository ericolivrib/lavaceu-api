package com.erico.ceu.lavaceu.domain.horario.dto;

import com.erico.ceu.lavaceu.domain.horario.DiaSemana;
import com.erico.ceu.lavaceu.domain.horario.Horario;

import java.time.LocalTime;
import java.util.UUID;

public record HorarioResponse(UUID id, DiaSemana diaSemana, Horario.Status status, LocalTime hora) {

    public static HorarioResponse fromHorarioEntity(Horario horario) {
        return new HorarioResponse(horario.getId(), horario.getDiaSemana(), horario.getStatus(), horario.getHora());
    }

}
