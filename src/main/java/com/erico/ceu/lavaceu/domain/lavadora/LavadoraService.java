package com.erico.ceu.lavaceu.domain.lavadora;

import com.erico.ceu.lavaceu.domain.lavadora.dto.CriarLavadoraRequest;
import com.erico.ceu.lavaceu.domain.lavadora.dto.LavadoraResponse;
import com.erico.ceu.lavaceu.domain.lavadora.exception.LavadoraNaoEncontradaException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LavadoraService {

    private final LavadoraRepository lavadoraRepository;

    public LavadoraService(LavadoraRepository lavadoraRepository) {
        this.lavadoraRepository = lavadoraRepository;
    }

    public List<LavadoraResponse> getLavadoras() {
        List<Lavadora> lavadoras = lavadoraRepository.findAll();

        return lavadoras.stream()
                .map(LavadoraResponse::fromMaquinaEntity)
                .toList();
    }

    public LavadoraResponse getLavadoraById(UUID id) {
        Lavadora lavadora = lavadoraRepository.findById(id).orElseThrow(LavadoraNaoEncontradaException::new);
        return LavadoraResponse.fromMaquinaEntity(lavadora);
    }

    public UUID adicionarLavadora(CriarLavadoraRequest criarLavadoraRequest) {
        Lavadora lavadora = criarLavadoraRequest.toMaquinaEntity();
        var lavadoraSalva = lavadoraRepository.save(lavadora);

        return lavadoraSalva.getId();
    }

}
