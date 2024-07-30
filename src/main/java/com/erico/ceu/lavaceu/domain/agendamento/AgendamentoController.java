package com.erico.ceu.lavaceu.domain.agendamento;

import com.erico.ceu.lavaceu.domain.agendamento.dto.AgendamentoResponse;
import com.erico.ceu.lavaceu.domain.agendamento.dto.ConfirmarAgendamentoRequest;
import com.erico.ceu.lavaceu.domain.agendamento.dto.CriarAgendamentoRequest;
import com.erico.ceu.lavaceu.util.ControllerUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping(path = "/agendamentos", consumes = "application/json")
    public ResponseEntity<Void> criarAgendamento(@RequestBody CriarAgendamentoRequest request) {
        var agendamentoId = agendamentoService.agendar(request);
        var agendamentoLocation = ControllerUtils.buildResourceLocationUri("/{agendamentoId}", agendamentoId);

        return ResponseEntity.created(agendamentoLocation).build();
    }

    @GetMapping(path = "/agendamentos", produces = "application/json")
    public ResponseEntity<List<AgendamentoResponse>> getAgendamentos() {
        var agendamentos = agendamentoService.getAgendamentos();
        return ResponseEntity.ok(agendamentos);
    }

    @PutMapping(path = "/agendamentos/{agendamentoId}/confirmado")
    public ResponseEntity<Void> confirmarAgendamento(@RequestBody ConfirmarAgendamentoRequest request, @PathVariable("agendamentoId") UUID agendamentoId) {
        agendamentoService.confirmarAgendamento(request, agendamentoId);
        return ResponseEntity.noContent().build();
    }

}
