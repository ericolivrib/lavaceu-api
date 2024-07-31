package com.erico.ceu.lavaceu.domain.horario.dto;

import com.erico.ceu.lavaceu.domain.horario.Horario;
import com.erico.ceu.lavaceu.domain.horario.HorarioLiberado;

import java.time.LocalDate;
import java.util.UUID;

public record LiberarHorarioRequest(LocalDate data) {

    public HorarioLiberado toHorarioLiberadoEntity(UUID horarioId) {
        Horario horario = new Horario();
        horario.setId(horarioId);

        return new HorarioLiberado(UUID.randomUUID(), horario, data, HorarioLiberado.Status.VAGO);
    }

}
