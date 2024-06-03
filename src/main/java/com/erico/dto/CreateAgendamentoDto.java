package com.erico.dto;

import java.time.LocalTime;

public record CreateAgendamentoDto(
	String nome,
	String matricula,
	Integer apartamento,
	String telefone,
	LocalTime horarioInicio,
	Integer maquinaId
) {
	
}
