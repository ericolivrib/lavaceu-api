package br.com.erico.lavanderia.model.horario;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalTime;

@Entity
@Table(name = "horarios")
public class HorarioAgendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "horario_id")
    public Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "periodo")
    public PeriodoDia periodo;

    @NotNull(message = "Informe a hora")
    @HorarioValido
    @Column(name = "hora")
    public LocalTime horario;

    public HorarioAgendamento() {
    }

    public HorarioAgendamento(Long id, PeriodoDia periodo, LocalTime horario) {
        this.id = id;
        this.periodo = periodo;
        this.horario = horario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PeriodoDia getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoDia periodo) {
        this.periodo = periodo;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime hora) {
        this.horario = hora;
    }
}
