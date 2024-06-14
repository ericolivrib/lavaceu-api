package com.erico.lavaceu.permissao;

import java.util.List;

import com.erico.lavaceu.usuario.UsuarioEntity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Id;

public class PermissaoEntity extends PanacheEntityBase {

	@Id
	public Integer id;
	public Permissao permissao;
	public List<UsuarioEntity> usuarios;

}
