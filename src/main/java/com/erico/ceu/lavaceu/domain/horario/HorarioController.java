package com.erico.ceu.lavaceu.domain.horario;

import com.erico.ceu.lavaceu.domain.horario.dto.CriarHorarioRequest;
import com.erico.ceu.lavaceu.domain.horario.dto.HorarioResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/horarios")
public class HorarioController {

    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @PostMapping(path = "", consumes = "application/json")
    public ResponseEntity<Void> adicionarHorario(@RequestBody CriarHorarioRequest request) {
        UUID horarioId = horarioService.adicionarHorario(request);
        URI location = URI.create("/horarios/" + horarioId);

        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<List<HorarioResponse>> getHorarios() {
        var horarios = horarioService.getHorarios();

        return ResponseEntity.ok(horarios);
    }

}
