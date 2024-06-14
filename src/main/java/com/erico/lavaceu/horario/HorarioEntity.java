package com.erico.lavaceu.horario;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import com.erico.lavaceu.agendamento.AgendamentoEntity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Id;

public class HorarioEntity extends PanacheEntityBase {

	@Id
	public UUID id;
	public LocalTime horarioInicio;
	public LocalTime horarioFim;
	public PeriodoDia periodo;
	public DiaSemana dia;
	public List<AgendamentoEntity> agendamentos;

}
