package com.erico.ceu.lavaceu.domain.lavadora.dto;

import com.erico.ceu.lavaceu.domain.lavadora.Lavadora;

import java.time.Duration;

public record CriarLavadoraRequest(String marca, Integer numero, Long tempoLavagem) {

    public Lavadora toMaquinaEntity() {
        return new Lavadora(null, marca, numero, Lavadora.Status.FUNCIONANDO, tempoLavagem);
    }

}
