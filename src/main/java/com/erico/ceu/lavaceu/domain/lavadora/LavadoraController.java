package com.erico.ceu.lavaceu.domain.lavadora;

import com.erico.ceu.lavaceu.domain.lavadora.dto.CriarLavadoraRequest;
import com.erico.ceu.lavaceu.domain.lavadora.dto.LavadoraResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/lavadoras")
public class LavadoraController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LavadoraController.class);

    private final LavadoraService lavadoraService;

    public LavadoraController(LavadoraService lavadoraService) {
        this.lavadoraService = lavadoraService;
    }

    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<List<LavadoraResponse>> getLavadoras() {
        var maquinas = lavadoraService.getLavadoras();
        return ResponseEntity.ok(maquinas);
    }

    @GetMapping(path = "/{lavadoraId}", produces = "application/json")
    public ResponseEntity<LavadoraResponse> getLavadora(@PathVariable("lavadoraId") UUID lavadoraId) {
        var maquina = lavadoraService.getLavadoraById(lavadoraId);
        return ResponseEntity.ok(maquina);
    }

    @PostMapping(path = "", consumes = "application/json")
    public ResponseEntity<CriarLavadoraRequest> adicionarLavadora(@RequestBody CriarLavadoraRequest request) {
        UUID lavadoraId = lavadoraService.adicionarLavadora(request);
        URI location = URI.create("/lavadoras/" + lavadoraId);

        return ResponseEntity.created(location).build();
    }

}
