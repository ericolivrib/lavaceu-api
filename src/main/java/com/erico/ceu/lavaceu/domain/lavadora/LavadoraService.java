package com.erico.ceu.lavaceu.domain.lavadora;

import com.erico.ceu.lavaceu.domain.lavadora.dto.CriarLavadoraRequest;
import com.erico.ceu.lavaceu.domain.lavadora.dto.LavadoraResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class LavadoraService {

    private static final Logger log = LoggerFactory.getLogger(LavadoraService.class);

    private final LavadoraRepository lavadoraRepository;

    public LavadoraService(LavadoraRepository lavadoraRepository) {
        this.lavadoraRepository = lavadoraRepository;
    }

    public List<LavadoraResponse> getLavadoras() {
        List<Lavadora> lavadoras = lavadoraRepository.findAll();
        return lavadoras.stream().map(LavadoraResponse::fromMaquinaEntity).toList();
    }

    public LavadoraResponse getLavadoraById(UUID id) {
        Lavadora lavadora = lavadoraRepository.findById(id).orElseThrow(() -> {
            log.error("Lavadora não encontrada");
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Lavadora não encontrada");
        });
        return LavadoraResponse.fromMaquinaEntity(lavadora);
    }

    public UUID adicionarLavadora(CriarLavadoraRequest criarLavadoraRequest) {
        Lavadora lavadora = criarLavadoraRequest.toMaquinaEntity();
        var lavadoraSalva = lavadoraRepository.save(lavadora);

        return lavadoraSalva.getId();
    }

}
