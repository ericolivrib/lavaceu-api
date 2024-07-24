package com.erico.ceu.lavaceu.domain.usuario.dto;

import com.erico.ceu.lavaceu.domain.usuario.Usuario;

import java.util.UUID;

public record UsuarioResponse(UUID id, String nome, String email, String matricula, String telefone, Integer apartamento) {

    public static UsuarioResponse fromMoradorEntity(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getMatricula(),
                usuario.getTelefone(),
                usuario.getApartamento()
        );
    }

}
