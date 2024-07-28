package com.erico.ceu.lavaceu.domain.agendamento.dto;

import com.erico.ceu.lavaceu.domain.agendamento.Agendamento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record AgendamentoResponse(UUID id, LocalDate data, LocalTime hora) {

    public static AgendamentoResponse fromAgendamentoEntity(Agendamento agendamento) {
        return new AgendamentoResponse(
                agendamento.getId(),
                agendamento.getHorarioDisponivel().getData(),
                agendamento.getHorarioDisponivel().getHorario().getHora()
        );
    }

}
