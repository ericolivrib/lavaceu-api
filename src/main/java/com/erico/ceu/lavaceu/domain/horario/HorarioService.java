package com.erico.ceu.lavaceu.domain.horario;

import com.erico.ceu.lavaceu.domain.horario.dto.CriarHorarioRequest;
import com.erico.ceu.lavaceu.domain.horario.dto.HorarioResponse;
import com.erico.ceu.lavaceu.domain.horario.dto.LiberarHorarioRequest;
import com.erico.ceu.lavaceu.domain.horario.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class HorarioService {

    private static final Logger log = LoggerFactory.getLogger(HorarioService.class);

    private final HorarioRepository horarioRepository;
    private final HorarioLiberadoRepository horarioLiberadoRepository;

    public HorarioService(HorarioRepository horarioRepository, HorarioLiberadoRepository horarioLiberadoRepository) {
        this.horarioRepository = horarioRepository;
        this.horarioLiberadoRepository = horarioLiberadoRepository;
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

    public UUID liberarHorario(LiberarHorarioRequest liberarHorarioRequest, UUID horarioId) {
        Horario horario = horarioRepository.findById(horarioId).orElseThrow(() -> {
            log.error("Tentativa de liberação de horário inexistente");
            return new HorarioNaoEncontradoException();
        });

        if (liberarHorarioRequest.data().isBefore(LocalDate.now())) {
            log.error("Tentativa de liberação de horário com data anterior ao dia de hoje: ({})", liberarHorarioRequest.data());
            throw new DataHorarioPassadaException();
        } else if (horarioLiberadoRepository.existsByHorarioIdAndData(horarioId, liberarHorarioRequest.data())) {
            log.error("Tentativa de liberação de horário já liberado nesta data: ({}, {} às {})",
                    horario.getDiaSemana(), liberarHorarioRequest.data(), horario.getHora());
            throw new HorarioJaLiberadoException();
        }

        HorarioLiberado horarioLiberadoSalvo = horarioLiberadoRepository.save(liberarHorarioRequest.toHorarioLiberadoEntity(horarioId));
        return horarioLiberadoSalvo.getId();
    }

}
