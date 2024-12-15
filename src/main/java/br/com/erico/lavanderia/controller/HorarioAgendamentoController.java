package br.com.erico.lavanderia.controller;

import br.com.erico.lavanderia.model.horario.HorarioAgendamento;
import br.com.erico.lavanderia.model.horario.PeriodoDia;
import br.com.erico.lavanderia.service.HorarioAgendamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/horarios-agendamento")
public class HorarioAgendamentoController {

    private final HorarioAgendamentoService horarioAgendamentoService;

    public HorarioAgendamentoController(HorarioAgendamentoService horarioAgendamentoService) {
        this.horarioAgendamentoService = horarioAgendamentoService;
    }

    @PostMapping
    public ResponseEntity<Void> createHorario(@RequestBody @Valid HorarioAgendamento horarioAgendamento, UriComponentsBuilder uriBuilder) {
        horarioAgendamentoService.create(horarioAgendamento);
        URI uri = uriBuilder.path("/horarios-agendamento/{id}").buildAndExpand(horarioAgendamento.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<HorarioAgendamento>> getHorarioByPeriodo() {
        List<HorarioAgendamento> horarios = horarioAgendamentoService.getAll();
        return ResponseEntity.ok(horarios);
    }

    @GetMapping(params = "periodo")
    public ResponseEntity<List<HorarioAgendamento>> getHorarioByPeriodo(@RequestParam("periodo") PeriodoDia periodoDia) {
        List<HorarioAgendamento> horarios = horarioAgendamentoService.getAllByPeriodo(periodoDia);
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioAgendamento> getHorarioByPeriodo(@PathVariable("id") long horarioId) {
        HorarioAgendamento horario = horarioAgendamentoService.getById(horarioId);
        return ResponseEntity.ok(horario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarHorario(@PathVariable("id") long horarioId, @RequestBody @Valid HorarioAgendamento horarioAgendamento) {
        horarioAgendamentoService.atualizar(horarioId, horarioAgendamento);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorario(@PathVariable("id") long horarioId) {
        horarioAgendamentoService.deletar(horarioId);
        return ResponseEntity.noContent().build();
    }

}
