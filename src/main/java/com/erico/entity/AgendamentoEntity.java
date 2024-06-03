package com.erico.entity;

import java.util.UUID;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

public class AgendamentoEntity extends PanacheEntity {

	public UUID id;
	public HorarioEntity horario;
	public UsuarioEntity morador;
	public MaquinaEntity maquina;
	public StatusEntity status;

}
