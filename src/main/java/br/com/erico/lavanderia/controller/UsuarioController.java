package br.com.erico.lavanderia.controller;

import br.com.erico.lavanderia.model.usuario.Usuario;
import br.com.erico.lavanderia.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Transactional
    @PostMapping("/moradores")
    public ResponseEntity<Void> adicionarMorador(@RequestBody @Valid Usuario morador, UriComponentsBuilder uriBuilder) {
        usuarioService.adicionarMorador(morador);

        URI moradorLocation = uriBuilder.path("/usuarios/moradores/{id}")
                .buildAndExpand(morador.getId())
                .toUri();

        return ResponseEntity.created(moradorLocation).build();
    }
}
