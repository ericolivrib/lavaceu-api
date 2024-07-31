package com.erico.ceu.lavaceu.domain.agendamento.dto;

import com.erico.ceu.lavaceu.domain.agendamento.Agendamento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record AgendamentoResponse(UUID id, String status, LocalDate data, LocalTime hora, String diaSemana, UsuarioAgendamentoResponse usuario) {

    public static AgendamentoResponse fromAgendamentoEntity(Agendamento agendamento) {
        return new AgendamentoResponse(
                agendamento.getId(),
                agendamento.getStatus().getNome(),
                agendamento.getHorarioDisponivel().getData(),
                agendamento.getHorarioDisponivel().getHorario().getHora(),
                agendamento.getHorarioDisponivel().getHorario().getDiaSemana().getDia(),
                new UsuarioAgendamentoResponse(
                        agendamento.getMorador().getId(),
                        agendamento.getMorador().getNome(),
                        agendamento.getMorador().getMatricula()
                )
        );
    }

}
