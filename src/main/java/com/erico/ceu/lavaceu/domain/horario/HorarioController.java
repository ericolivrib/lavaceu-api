package com.erico.ceu.lavaceu.domain.horario;

import com.erico.ceu.lavaceu.domain.horario.dto.CriarHorarioRequest;
import com.erico.ceu.lavaceu.domain.horario.dto.HorarioResponse;
import com.erico.ceu.lavaceu.domain.horario.dto.LiberarHorarioRequest;
import com.erico.ceu.lavaceu.util.ControllerUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class HorarioController {

    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @PostMapping(path = "/horarios", consumes = "application/json")
    public ResponseEntity<Void> adicionarHorario(@RequestBody CriarHorarioRequest request) {
        UUID horarioId = horarioService.adicionarHorario(request);

        var location = ControllerUtils.buildResourceLocationUri("/{horarioId}", horarioId);

        return ResponseEntity.created(location).build();
    }

    @PostMapping(path = "/horarios/{horarioId}")
    public ResponseEntity<Void> liberarHorario(@RequestBody LiberarHorarioRequest request, @PathVariable("horarioId") UUID horarioId) {
        UUID horarioDisponivelId = horarioService.liberaHorario(request, horarioId);
        var horarioDisponivelLocation = ControllerUtils.buildResourceLocationUri("/{horarioLiberadoId}", horarioDisponivelId);

        return ResponseEntity.created(horarioDisponivelLocation).build();
    }

    @GetMapping(path = "/horarios", produces = "application/json")
    public ResponseEntity<List<HorarioResponse>> getHorarios() {
        var horarios = horarioService.getHorarios();
        return ResponseEntity.ok(horarios);
    }

    @DeleteMapping(path = "/horarios/{horarioId}")
    public ResponseEntity<Void> deletarHorario(@PathVariable("horarioId") UUID horarioId) {
        horarioService.deletarHorario(horarioId);
        return ResponseEntity.noContent().build();
    }

}
