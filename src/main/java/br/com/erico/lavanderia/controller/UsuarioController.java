package br.com.erico.lavanderia.controller;

import br.com.erico.lavanderia.dto.UsuarioProjection;
import br.com.erico.lavanderia.model.acesso.TipoAcesso;
import br.com.erico.lavanderia.model.usuario.Usuario;
import br.com.erico.lavanderia.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Transactional
    @PostMapping("/moradores")
    public ResponseEntity<Void> cadastrarMorador(@RequestBody @Valid Usuario morador, UriComponentsBuilder uriBuilder) {
        usuarioService.cadastrarMorador(morador);

        URI moradorLocation = uriBuilder.path("/usuarios/moradores/{id}")
                .buildAndExpand(morador.getId())
                .toUri();

        return ResponseEntity.created(moradorLocation).build();
    }

    @Transactional
    @PostMapping("/bolsistas/{id}")
    public ResponseEntity<Void> cadastrarBolsista(@PathVariable("id") Long usuarioId, UriComponentsBuilder uriBuilder) {
        usuarioService.cadastrarBolsista(usuarioId);

        URI bolsistaLocation = uriBuilder.path("/usuarios/moradores/{id}")
                .buildAndExpand(usuarioId)
                .toUri();

        return ResponseEntity.created(bolsistaLocation).build();
    }

    @GetMapping("/moradores")
    public ResponseEntity<List<UsuarioProjection>> getAllMoradores() {
        List<UsuarioProjection> moradores = usuarioService.getUsuariosByAcesso(TipoAcesso.MORADOR);
        return ResponseEntity.ok(moradores);
    }

    @GetMapping("/bolsistas")
    public ResponseEntity<List<UsuarioProjection>> getAllBolsistas() {
        List<UsuarioProjection> moradores = usuarioService.getUsuariosByAcesso(TipoAcesso.BOLSISTA);
        return ResponseEntity.ok(moradores);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarUsuario(@PathVariable("id") Long usuarioId, @RequestBody @Valid Usuario usuario) {
        usuarioService.atualizarUsuario(usuarioId, usuario);
        return ResponseEntity.noContent().build();
    }
}
