package com.erico.ceu.lavaceu.domain.usuario;

import com.erico.ceu.lavaceu.domain.usuario.dto.CriarUsuarioRequest;
import com.erico.ceu.lavaceu.domain.usuario.dto.UsuarioResponse;
import com.erico.ceu.lavaceu.util.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<List<UsuarioResponse>> getUsuarios() {
        var usuarios = usuarioService.getUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping(path = "", consumes = "application/json")
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody CriarUsuarioRequest request) {
        UUID usuarioId = usuarioService.cadastrarUsuarios(request);
        var location = ControllerUtils.buildResourceLocationUri("/{usuarioId}", usuarioId);

        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/{usuarioId}", produces = "application/json")
    public ResponseEntity<UsuarioResponse> getUsuarioById(@PathVariable("usuarioId") UUID moradorId) {
        var usuario = usuarioService.getUsuariosById(moradorId);
        return ResponseEntity.ok(usuario);
    }

}
