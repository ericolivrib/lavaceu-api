package com.erico.ceu.lavaceu.domain.horario;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class HorarioDisponivel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;

    private LocalDate data;

    private Status status;

    public enum Status {
        VAGO,
        OCUPADO,
        CANCELADO
    }

    public HorarioDisponivel() {
    }

    public HorarioDisponivel(UUID id, Horario horario, LocalDate data) {
        this.id = id;
        this.horario = horario;
        this.data = data;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
