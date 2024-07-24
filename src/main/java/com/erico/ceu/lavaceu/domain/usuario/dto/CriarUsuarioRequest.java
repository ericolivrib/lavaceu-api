package com.erico.ceu.lavaceu.domain.usuario.dto;

import com.erico.ceu.lavaceu.domain.permissao.Permissao;
import com.erico.ceu.lavaceu.domain.usuario.Usuario;

public record CriarUsuarioRequest(String nome, String email, String matricula, String telefone, Integer apartamento) {

    public Usuario toMoradorEntity() {
        return new Usuario(null, nome, email, matricula, telefone, apartamento, new Permissao(Permissao.Values.MORADOR));
    }

}
