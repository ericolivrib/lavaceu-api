package com.erico.ceu.lavaceu.domain.agendamento.dto;

import java.util.UUID;

public record UsuarioAgendamentoResponse(UUID id, String nome, String matricula) {
}
