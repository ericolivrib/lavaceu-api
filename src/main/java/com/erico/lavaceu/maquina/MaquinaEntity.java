package com.erico.lavaceu.maquina;

import java.time.Duration;
import java.util.List;

import com.erico.lavaceu.agendamento.AgendamentoEntity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Id;

public class MaquinaEntity extends PanacheEntityBase {
	
	@Id
	public Integer id;
	public Duration tempoLavagem;
	public Boolean funcionando;
	public List<AgendamentoEntity> agendamentos;

}
