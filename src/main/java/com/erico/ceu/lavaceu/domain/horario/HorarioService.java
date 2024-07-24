package com.erico.ceu.lavaceu.domain.horario;

import com.erico.ceu.lavaceu.domain.horario.dto.CriarHorarioRequest;
import com.erico.ceu.lavaceu.domain.horario.dto.HorarioResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HorarioService {

    private final HorarioRepository horarioRepository;

    public HorarioService(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    public UUID adicionarHorario(CriarHorarioRequest criarHorarioRequest) {
        Horario horario = criarHorarioRequest.toHorarioEntity();
        var horarioSalvo = horarioRepository.save(horario);

        return horarioSalvo.getId();
    }

    public List<HorarioResponse> getHorarios() {
        var horarios = horarioRepository.findAll();

        return horarios.stream()
                .map(HorarioResponse::fromHorarioEntity)
                .toList();
    }

}
