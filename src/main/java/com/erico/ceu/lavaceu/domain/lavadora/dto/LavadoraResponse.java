package com.erico.ceu.lavaceu.domain.lavadora.dto;

import com.erico.ceu.lavaceu.domain.lavadora.Lavadora;

import java.time.Duration;
import java.util.UUID;

public record LavadoraResponse(UUID id, String marca, Integer numero, Lavadora.Status status, Long tempoLavagem) {

    public static LavadoraResponse fromMaquinaEntity(Lavadora lavadora) {
        return new LavadoraResponse(
                lavadora.getId(),
                lavadora.getMarca(),
                lavadora.getNumero(),
                lavadora.getStatus(),
                lavadora.getTempoLavagem()
        );
    }

}
