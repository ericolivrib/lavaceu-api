package com.erico.lavaceu.status;

import java.util.List;

import com.erico.lavaceu.agendamento.AgendamentoEntity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Id;

public class StatusEntity extends PanacheEntityBase {
	
	@Id
	public Integer id;
	public StatusAgendamento status;
	public List<AgendamentoEntity> agendamentos;

}
