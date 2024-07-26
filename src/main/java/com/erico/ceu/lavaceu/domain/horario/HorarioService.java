package com.erico.ceu.lavaceu.domain.horario;

import com.erico.ceu.lavaceu.domain.horario.dto.CriarHorarioRequest;
import com.erico.ceu.lavaceu.domain.horario.dto.HorarioResponse;
import com.erico.ceu.lavaceu.domain.horario.exception.HorarioJaExistenteException;
import com.erico.ceu.lavaceu.domain.horario.exception.PeriodoDiaInvalidoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HorarioService {

    private static final Logger log = LoggerFactory.getLogger(HorarioService.class);

    private final HorarioRepository horarioRepository;
    private final HorarioDisponivelRepository horarioDisponivelRepository;

    public HorarioService(HorarioRepository horarioRepository, HorarioDisponivelRepository horarioDisponivelRepository) {
        this.horarioRepository = horarioRepository;
        this.horarioDisponivelRepository = horarioDisponivelRepository;
    }

    public UUID adicionarHorario(CriarHorarioRequest criarHorarioRequest) {
        if (horarioRepository.existsByDiaSemanaAndHora(criarHorarioRequest.diaSemana(), criarHorarioRequest.hora())) {
            log.error("Tentativa de cadastro de horários com dia da semana e hora já existentes");
            throw new HorarioJaExistenteException();
        }

        UUID horarioId;

        try {
            Horario horarioSalvo = horarioRepository.save(criarHorarioRequest.toHorarioEntity());
            horarioId = horarioSalvo.getId();
        } catch (PeriodoDiaInvalidoException e) {
            log.error("Tentativa de cadastro de horário com hora inválida: ({})", criarHorarioRequest.hora());
            throw e;
        }

        return horarioId;
    }

    public List<HorarioResponse> getHorarios() {
        var horarios = horarioRepository.findAll();
        return horarios.stream().map(HorarioResponse::fromHorarioEntity).toList();
    }

    public void deletarHorario(UUID id) {
        horarioRepository.deleteById(id);
    }

}
