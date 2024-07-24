package com.erico.ceu.lavaceu.domain.horario;

import com.erico.ceu.lavaceu.domain.agendamento.Agendamento;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.ORDINAL)
    private DiaSemana diaSemana;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    private LocalTime hora;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Agendamento> agendamentos;

    public enum Status {
        LIBERADO,
        BLOQUEADO,
        OCUPADO
    }

    public Horario() {
    }

    public Horario(UUID id, DiaSemana diaSemana, Status status, LocalTime hora) {
        this.id = id;
        this.diaSemana = diaSemana;
        this.status = status;
        this.hora = hora;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

}
