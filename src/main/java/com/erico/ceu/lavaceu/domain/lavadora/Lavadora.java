package com.erico.ceu.lavaceu.domain.lavadora;

import com.erico.ceu.lavaceu.domain.agendamento.Agendamento;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Lavadora {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String marca;

    private Integer numero;

    private Status status;

    private Long tempoLavagem;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Agendamento> agendamentos;

    public enum Status {
        FUNCIONANDO,
        QUEBRADA
    }

    public Lavadora() {
    }

    public Lavadora(UUID id, String marca, Integer numero, Status status, Long tempoLavagem) {
        this.id = id;
        this.marca = marca;
        this.numero = numero;
        this.status = status;
        this.tempoLavagem = tempoLavagem;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getTempoLavagem() {
        return tempoLavagem;
    }

    public void setTempoLavagem(Long tempoLavagem) {
        this.tempoLavagem = tempoLavagem;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

}
