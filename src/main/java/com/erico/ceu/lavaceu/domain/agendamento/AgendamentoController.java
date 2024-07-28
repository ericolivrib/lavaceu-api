package com.erico.ceu.lavaceu.domain.agendamento;

import com.erico.ceu.lavaceu.domain.agendamento.dto.AgendamentoResponse;
import com.erico.ceu.lavaceu.domain.agendamento.dto.CriarAgendamentoRequest;
import com.erico.ceu.lavaceu.util.ControllerUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
