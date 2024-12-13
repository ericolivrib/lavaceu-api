package br.com.erico.lavanderia.controller;

import br.com.erico.lavanderia.dto.EstadoLavadoraDto;
import br.com.erico.lavanderia.model.lavadora.Lavadora;
import br.com.erico.lavanderia.service.LavadoraService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lavadoras")
public class LavadoraController {

    private final LavadoraService lavadoraService;

    public LavadoraController(LavadoraService lavadoraService) {
        this.lavadoraService = lavadoraService;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarLavadora(@RequestBody @Valid Lavadora lavadora, UriComponentsBuilder uriBuilder) {
        lavadoraService.cadastrarLavadora(lavadora);
        URI uri = uriBuilder.path("/lavadoras/{id}").build(lavadora.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Lavadora>> getAllLavadoras() {
        List<Lavadora> lavadora = lavadoraService.getAllLLavadoras();
        return ResponseEntity.ok(lavadora);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lavadora> getLavadoraById(@PathVariable("id") long lavadoraId) {
        Lavadora lavadora = lavadoraService.getLavadoraById(lavadoraId);
        return ResponseEntity.ok(lavadora);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarLavadora(@PathVariable("id") long lavadoraId, @Valid @RequestBody Lavadora lavadora) {
        lavadoraService.atualizarLavadora(lavadoraId, lavadora);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Void> atualizarEstadoLavadora(@PathVariable("id") long lavadoraId, @RequestBody EstadoLavadoraDto estadoLavadoraDto) {
        lavadoraService.atualizarEstadoLavadora(lavadoraId, estadoLavadoraDto.estado());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLavadora(@PathVariable("id") long lavadoraId) {
        lavadoraService.deletarLavadora(lavadoraId);
        return ResponseEntity.noContent().build();
    }
}
