package com.erico.ceu.lavaceu.domain.horario;

import com.erico.ceu.lavaceu.domain.horario.dto.CriarHorarioRequest;
import com.erico.ceu.lavaceu.domain.horario.dto.HorarioResponse;
import com.erico.ceu.lavaceu.domain.horario.dto.LiberarHorarioRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            log.error("Horário já existente");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Horário já existente");
        }

        UUID horarioId;

        try {
            Horario horarioSalvo = horarioRepository.save(criarHorarioRequest.toHorarioEntity());
            horarioId = horarioSalvo.getId();
        } catch (PeriodoDiaInvalidoException e) {
            log.error("Período do dia inválido: ({})", criarHorarioRequest.hora());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Período do dia inválido");
        }

        return horarioId;
    }

    public List<HorarioResponse> getHorarios() {
        var horarios = horarioRepository.findAll();
        return horarios.stream().map(HorarioResponse::fromHorarioEntity).toList();
    }

    public void deletarHorario(UUID horarioId) {
        Horario horario = horarioRepository.findById(horarioId).orElseThrow(() -> {
            log.error("Horário não encontrado");
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Horário não encontrado");
        });

        if (horarioLiberadoRepository.existsByHorarioId(horarioId)) {
            log.error("Horário em uso");
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Horário em uso");
        }

        horarioRepository.delete(horario);
    }

    public UUID liberarHorario(LiberarHorarioRequest liberarHorarioRequest, UUID horarioId) {
        Horario horario = horarioRepository.findById(horarioId).orElseThrow(() -> {
            log.error("Horário não encontrado");
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Horário não encontrado");
        });

        if (liberarHorarioRequest.data().isBefore(LocalDate.now())) {
            log.error("Tentativa de liberação de horário com data anterior ao dia de hoje: ({})", liberarHorarioRequest.data());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Horário vencido");
        } else if (horarioLiberadoRepository.existsByHorarioIdAndData(horarioId, liberarHorarioRequest.data())) {
            log.error("Horário já liberado ({}, {} às {})",
                    horario.getDiaSemana(), liberarHorarioRequest.data(), horario.getHora());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Horário já liberado");
        }

        HorarioLiberado horarioLiberadoSalvo = horarioLiberadoRepository.save(liberarHorarioRequest.toHorarioLiberadoEntity(horarioId));
        return horarioLiberadoSalvo.getId();
    }

}
