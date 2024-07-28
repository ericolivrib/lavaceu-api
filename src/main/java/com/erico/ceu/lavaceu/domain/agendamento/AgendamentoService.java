package com.erico.ceu.lavaceu.domain.agendamento;

import com.erico.ceu.lavaceu.domain.agendamento.dto.CriarAgendamentoRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public UUID agendar(CriarAgendamentoRequest criarAgendamentoRequest) {
        Agendamento agendamentoSalvo = agendamentoRepository.save(criarAgendamentoRequest.toEntity());
        return agendamentoSalvo.getId();
    }

}
