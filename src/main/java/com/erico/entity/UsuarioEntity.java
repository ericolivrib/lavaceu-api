package com.erico.entity;

import java.util.UUID;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

public class UsuarioEntity extends PanacheEntity {
	
	public UUID id;
	public String nome;
	public String matricula;
	public Integer apartamento;
	public String telefone;
	public PermissaoEntity permissao;
	public List<AgendamentoEntity> agendamentos;

}
