package com.erico.entity;

import java.util.List;

import com.erico.enumeration.Permissao;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

public class PermissaoEntity extends PanacheEntity {

	public Integer id;
	public Permissao permissao;
	public List<UsuarioEntity> usuarios;

}
