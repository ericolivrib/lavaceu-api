package com.erico.entity;

import java.util.List;

import com.erico.enumeration.StatusAgendamento;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

public class StatusEntity extends PanacheEntity {
	
	public Integer id;
	public StatusAgendamento status;
	public List<AgendamentoEntity> agendamentos;

}
