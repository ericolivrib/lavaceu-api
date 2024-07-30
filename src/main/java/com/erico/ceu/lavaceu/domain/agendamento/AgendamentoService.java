package com.erico.ceu.lavaceu.domain.agendamento;

import com.erico.ceu.lavaceu.domain.agendamento.dto.AgendamentoResponse;
import com.erico.ceu.lavaceu.domain.agendamento.dto.ConfirmarAgendamentoRequest;
import com.erico.ceu.lavaceu.domain.agendamento.dto.CriarAgendamentoRequest;
import com.erico.ceu.lavaceu.domain.agendamento.exception.AgendamentoNaoEncontradoException;
import com.erico.ceu.lavaceu.domain.agendamento.exception.StatusAgendamentoInvalidoException;
import com.erico.ceu.lavaceu.domain.horario.HorarioLiberado;
import com.erico.ceu.lavaceu.domain.horario.HorarioLiberadoRepository;
import com.erico.ceu.lavaceu.domain.horario.exception.HorarioLiberadoCanceladoException;
import com.erico.ceu.lavaceu.domain.horario.exception.HorarioLiberadoNaoEncontradoException;
import com.erico.ceu.lavaceu.domain.horario.exception.HorarioLiberadoOcupadoException;
import com.erico.ceu.lavaceu.domain.lavadora.Lavadora;
import com.erico.ceu.lavaceu.domain.lavadora.LavadoraRepository;
import com.erico.ceu.lavaceu.domain.lavadora.exception.LavadoraNaoEncontradaException;
import com.erico.ceu.lavaceu.domain.lavadora.exception.LavadoraQuebradaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

        var horarioLiberado = horarioLiberadoRepository.findById(criarAgendamentoRequest.horarioLiberadoId())
                .orElseThrow(HorarioLiberadoNaoEncontradoException::new);

        if (horarioLiberado.getStatus() == HorarioLiberado.Status.OCUPADO) {
            log.error("Tentativa de cadastro de agendamento com horário ocupado, {}", horarioLiberado.getHorario());
            throw new HorarioLiberadoOcupadoException();
        } else if (horarioLiberado.getStatus() == HorarioLiberado.Status.CANCELADO) {
            log.error("Tentativa de cadastro de agendamento com horário cancelado");
            throw new HorarioLiberadoCanceladoException();
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
            log.error("Tentativa de confirmação de agendamento não existente");
            return new AgendamentoNaoEncontradoException();
        });

        if (agendamento.getStatus() != Agendamento.Status.AGENDADO) {
            log.error("Tentativa de confirmação de horário não agendado");
            throw new StatusAgendamentoInvalidoException();
        }

        Lavadora lavadora = lavadoraRepository.findById(confirmarAgendamentoRequest.lavadoraId()).orElseThrow(() -> {
            log.error("Tentativa de escolha de lavadora não existente");
            return new LavadoraNaoEncontradaException();
        });

        if (lavadora.getStatus() == Lavadora.Status.QUEBRADA) {
            log.error("Tentativa de confirmação de agendamento com lavadora quebrada");
            throw new LavadoraQuebradaException();
        }

        agendamento.setStatus(Agendamento.Status.CONFIRMADO);
        agendamento.setLavadora(lavadora);

        agendamentoRepository.save(agendamento);
    }

}
