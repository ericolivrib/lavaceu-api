package com.erico.ceu.lavaceu.domain.horario;

import com.erico.ceu.lavaceu.domain.agendamento.Agendamento;
import com.erico.ceu.lavaceu.domain.horario.exception.PeriodoDiaInvalidoException;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private DiaSemana diaSemana;

    @Enumerated(EnumType.STRING)
    private PeriodoDia periodoDia;

    private LocalTime hora;

    @ManyToOne
    private HorarioDisponivel horarioDisponivel;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Agendamento> agendamentos;

    public Horario() {
    }

    public Horario(UUID id, DiaSemana diaSemana, PeriodoDia periodoDia, LocalTime hora) {
        this.id = id;
        this.diaSemana = diaSemana;
        this.periodoDia = periodoDia;
        this.hora = hora;
    }

    public Horario(UUID id, DiaSemana diaSemana, LocalTime hora) throws PeriodoDiaInvalidoException {
        this.id = id;
        this.diaSemana = diaSemana;
        this.hora = hora;
        this.setPeriodoDia(hora);
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

    public PeriodoDia getPeriodoDia() {
        return periodoDia;
    }

    public void setPeriodoDia(PeriodoDia periodoDia) {
        this.periodoDia = periodoDia;
    }

    public void setPeriodoDia(LocalTime hora) throws PeriodoDiaInvalidoException {
        if ((hora.equals(PeriodoDia.MANHA.getHoraInicial()) || hora.isAfter(PeriodoDia.MANHA.getHoraInicial())) && hora.isBefore(PeriodoDia.MANHA.getHoraFinal())) {
            setPeriodoDia(PeriodoDia.MANHA);
        } else if ((hora.equals(PeriodoDia.TARDE.getHoraInicial()) || hora.isAfter(PeriodoDia.TARDE.getHoraInicial())) && hora.isBefore(PeriodoDia.TARDE.getHoraFinal())) {
            setPeriodoDia(PeriodoDia.TARDE);
        } else if ((hora.equals(PeriodoDia.NOITE.getHoraInicial()) || hora.isAfter(PeriodoDia.NOITE.getHoraInicial())) && hora.isBefore(PeriodoDia.NOITE.getHoraFinal())) {
            setPeriodoDia(PeriodoDia.NOITE);
        } else {
            throw new PeriodoDiaInvalidoException();
        }
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public HorarioDisponivel getHorarioDisponivel() {
        return horarioDisponivel;
    }

    public void setHorarioDisponivel(HorarioDisponivel horarioDisponivel) {
        this.horarioDisponivel = horarioDisponivel;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

}
