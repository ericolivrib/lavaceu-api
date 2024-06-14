package com.erico.lavaceu.agendamento;

import com.erico.lavaceu.agendamento.dto.CreateAgendamentoDto;
import com.erico.lavaceu.horario.HorarioEntity;
import com.erico.lavaceu.maquina.MaquinaEntity;
import com.erico.lavaceu.status.StatusAgendamento;
import com.erico.lavaceu.status.StatusEntity;
import com.erico.lavaceu.usuario.UsuarioEntity;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AgendamentoService {
	
	public void createUser(CreateAgendamentoDto createAgendamentoDto) {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.id = createAgendamentoDto.usuarioId();

		HorarioEntity horarioEntity = new HorarioEntity();
		horarioEntity.id = createAgendamentoDto.horarioIid();

		MaquinaEntity maquinaEntity = new MaquinaEntity();
		maquinaEntity.id = createAgendamentoDto.maquinaId();

		StatusEntity statusEntity = new StatusEntity();
		statusEntity.status = StatusAgendamento.AGENDADO;
		
		var agendamentoEntity = new AgendamentoEntity();
		agendamentoEntity.morador = usuarioEntity;
		agendamentoEntity.horario = horarioEntity;
		agendamentoEntity.maquina = maquinaEntity;
		agendamentoEntity.status = statusEntity;

		agendamentoEntity.persist();
	}

}
