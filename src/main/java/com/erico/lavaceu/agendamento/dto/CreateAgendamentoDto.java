package com.erico.lavaceu.agendamento.dto;

import java.time.LocalTime;
import java.util.UUID;

public record CreateAgendamentoDto(
	UUID usuarioId,
	UUID horarioIid,
	Integer maquinaId
) {
	
}
