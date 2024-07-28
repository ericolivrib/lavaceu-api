package com.erico.ceu.lavaceu.domain.agendamento.dto;

import com.erico.ceu.lavaceu.domain.agendamento.Agendamento;
import com.erico.ceu.lavaceu.domain.horario.HorarioLiberado;
import com.erico.ceu.lavaceu.domain.usuario.Usuario;

import java.util.UUID;

public record CriarAgendamentoRequest(
        UUID usuarioId,
        UUID horarioDisponivelId
) {

    public Agendamento toEntity() {
        HorarioLiberado horarioLiberado = new HorarioLiberado();
        horarioLiberado.setId(horarioDisponivelId);

        Usuario usuario = new Usuario();
        usuario.setId(usuarioId);

        return new Agendamento(UUID.randomUUID(), horarioLiberado, usuario, Agendamento.Status.FUTURO);
    }

}
