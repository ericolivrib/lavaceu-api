package com.erico.entity;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import com.erico.enumeration.PeriodoDia;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

public class HorarioEntity extends PanacheEntity {

	public UUID id;
	public LocalTime horarioInicio;
	public LocalTime horarioFim;
	public PeriodoDia periodo;
	public List<AgendamentoEntity> agendamentos;

}
