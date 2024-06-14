package com.erico.lavaceu.agendamento;

import java.util.UUID;

import com.erico.lavaceu.horario.HorarioEntity;
import com.erico.lavaceu.usuario.UsuarioEntity;
import com.erico.lavaceu.maquina.MaquinaEntity;
import com.erico.lavaceu.status.StatusEntity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class AgendamentoEntity extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	public UUID id;
	public HorarioEntity horario;
	public UsuarioEntity morador;
	public MaquinaEntity maquina;
	public StatusEntity status;

}
