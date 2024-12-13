package br.com.erico.lavanderia.model.horario;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@Entity
@Table(name = "horarios")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "horario_id")
    public Long id;

    @NotBlank(message = "Informe o período")
    @Pattern(regexp = "MANHA|TARDE|NOITE", message = "Período deve ser MANHA, TARDE ou NOITE")
    @Column(name = "periodo")
    public String periodo;

    @NotNull(message = "Informe a hora")
    @Column(name = "hora")
    public LocalDate hora;

    public Horario() {
    }

    public Horario(Long id, String periodo, LocalDate hora) {
        this.id = id;
        this.periodo = periodo;
        this.hora = hora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public LocalDate getHora() {
        return hora;
    }

    public void setHora(LocalDate hora) {
        this.hora = hora;
    }
}
