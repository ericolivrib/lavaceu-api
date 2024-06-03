package com.erico.entity;

import java.time.Duration;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

public class MaquinaEntity extends PanacheEntity {
	
	public Integer id;
	public Duration tempoLavagem;
	public Boolean funcionando;
	public List<AgendamentoEntity> agendamentos;

}
