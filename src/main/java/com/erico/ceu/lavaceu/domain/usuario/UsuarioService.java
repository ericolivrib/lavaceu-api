package com.erico.ceu.lavaceu.domain.usuario;

import com.erico.ceu.lavaceu.domain.usuario.dto.CriarUsuarioRequest;
import com.erico.ceu.lavaceu.domain.usuario.dto.UsuarioResponse;
import com.erico.ceu.lavaceu.domain.usuario.exception.UsuarioNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponse> getUsuarios() {
        List<Usuario> moradores = usuarioRepository.findAll();

        return moradores.stream()
                .map(UsuarioResponse::fromMoradorEntity)
                .toList();
    }

    public UsuarioResponse getUsuariosById(UUID id) {
        Usuario usuarioEntity = usuarioRepository.findById(id).orElseThrow(UsuarioNaoEncontradoException::new);
        return UsuarioResponse.fromMoradorEntity(usuarioEntity);
    }

    public UUID cadastrarUsuarios(CriarUsuarioRequest criarUsuarioRequest) {
        Usuario novoUsuario = criarUsuarioRequest.toMoradorEntity();
        var usuarioSalvo = usuarioRepository.save(novoUsuario);

        return usuarioSalvo.getId();
    }

}
