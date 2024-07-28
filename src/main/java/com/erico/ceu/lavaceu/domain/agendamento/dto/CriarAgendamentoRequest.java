package com.erico.ceu.lavaceu.domain.agendamento.dto;

import com.erico.ceu.lavaceu.domain.agendamento.Agendamento;
import com.erico.ceu.lavaceu.domain.horario.HorarioDisponivel;
import com.erico.ceu.lavaceu.domain.usuario.Usuario;

import java.util.UUID;

public record CriarAgendamentoRequest(
        UUID usuarioId,
        UUID horarioDisponivelId
) {

    public Agendamento toEntity() {
        HorarioDisponivel horarioDisponivel = new HorarioDisponivel();
        horarioDisponivel.setId(horarioDisponivelId);

        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);

        return new Agendamento(UUID.randomUUID(), horarioDisponivel, usuario, Agendamento.Status.FUTURO);
    }

}
