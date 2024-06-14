package com.erico.lavaceu.usuario;

import java.util.UUID;

import com.erico.lavaceu.agendamento.AgendamentoEntity;
import com.erico.lavaceu.permissao.PermissaoEntity;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class UsuarioEntity extends PanacheEntityBase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	public UUID id;
	public String nome;
	public String matricula;
	public Integer apartamento;
	public String telefone;
	public PermissaoEntity permissao;
	public List<AgendamentoEntity> agendamentos;

}
