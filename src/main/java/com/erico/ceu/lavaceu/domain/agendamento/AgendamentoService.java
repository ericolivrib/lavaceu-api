package com.erico.ceu.lavaceu.domain.agendamento;

import com.erico.ceu.lavaceu.domain.agendamento.dto.AgendamentoResponse;
import com.erico.ceu.lavaceu.domain.agendamento.dto.ConfirmarAgendamentoRequest;
import com.erico.ceu.lavaceu.domain.agendamento.dto.CriarAgendamentoRequest;
import com.erico.ceu.lavaceu.domain.horario.HorarioLiberado;
import com.erico.ceu.lavaceu.domain.horario.HorarioLiberadoRepository;
import com.erico.ceu.lavaceu.domain.lavadora.Lavadora;
import com.erico.ceu.lavaceu.domain.lavadora.LavadoraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AgendamentoService {

    private static final Logger log = LoggerFactory.getLogger(AgendamentoService.class);

    private final AgendamentoRepository agendamentoRepository;
    private final HorarioLiberadoRepository horarioLiberadoRepository;
    private final LavadoraRepository lavadoraRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, HorarioLiberadoRepository horarioLiberadoRepository, LavadoraRepository lavadoraRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.horarioLiberadoRepository = horarioLiberadoRepository;
        this.lavadoraRepository = lavadoraRepository;
    }

    public UUID agendar(CriarAgendamentoRequest criarAgendamentoRequest) {
        // todo: buscar horário disponível por UUID e verificar se seu status está VAGO

        var horarioLiberado = horarioLiberadoRepository.findById(criarAgendamentoRequest.horarioLiberadoId()).orElseThrow(() -> {
            log.error("Horário não liberado");
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Horário não encontrado");
        });

        if (horarioLiberado.getStatus() == HorarioLiberado.Status.OCUPADO) {
            log.error("Horário ocupado");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Horário ocupado");
        } else if (horarioLiberado.getStatus() == HorarioLiberado.Status.CANCELADO) {
            log.error("Horário cancelado");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Horário cancelado");
        }

        horarioLiberado.setStatus(HorarioLiberado.Status.OCUPADO);
        horarioLiberadoRepository.save(horarioLiberado);

        Agendamento agendamentoSalvo = agendamentoRepository.save(criarAgendamentoRequest.toEntity());
        return agendamentoSalvo.getId();
    }

    public List<AgendamentoResponse> getAgendamentos() {
        var agendamentos = agendamentoRepository.findAll();
        return agendamentos.stream().map(AgendamentoResponse::fromAgendamentoEntity).toList();
    }

    public void confirmarAgendamento(ConfirmarAgendamentoRequest confirmarAgendamentoRequest, UUID agendamentoId) {
        Agendamento agendamento = agendamentoRepository.findById(agendamentoId).orElseThrow(() -> {
            log.error("Agendamento não encontrado");
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado");
        });

        if (agendamento.getStatus() != Agendamento.Status.AGENDADO) {
            log.error("Horário não agendado");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Horário não agendado");
        }

        Lavadora lavadora = lavadoraRepository.findById(confirmarAgendamentoRequest.lavadoraId()).orElseThrow(() -> {
            log.error("Lavadora não encontrada");
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Lavadora não encontrada");
        });

        if (lavadora.getStatus() == Lavadora.Status.QUEBRADA) {
            log.error("Lavadora quebrada");
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Lavadora quebrada");
        }

        agendamento.setStatus(Agendamento.Status.CONFIRMADO);
        agendamento.setLavadora(lavadora);

        agendamentoRepository.save(agendamento);
    }

}
