package com.erico.ceu.lavaceu.domain.usuario;

import com.erico.ceu.lavaceu.domain.usuario.dto.CriarUsuarioRequest;
import com.erico.ceu.lavaceu.domain.usuario.dto.UsuarioResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

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
        Usuario usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> {
            log.error("Usuário não encontrado");
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        });
        return UsuarioResponse.fromMoradorEntity(usuarioEntity);
    }

    public UUID cadastrarUsuarios(CriarUsuarioRequest criarUsuarioRequest) {
        if (usuarioRepository.existsByMatricula(criarUsuarioRequest.matricula())) {
            log.error("Usuário já existente");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário já existe");
        }

        var usuarioSalvo = usuarioRepository.save(criarUsuarioRequest.toMoradorEntity());
        return usuarioSalvo.getId();
    }

}
